package org.hh99.gradation.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.jwt.JwtUtil;
import org.hh99.gradation.repository.CardRepository;
import org.hh99.gradation.repository.ColumnsRepository;
import org.hh99.gradation.repository.CommentRepository;
import org.hh99.gradation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardService {

	private final UserRepository userRepository;
	private final CardRepository cardRepository;
	private final ColumnsRepository columnsRepository;
	private final CommentRepository commentRepository;
	private final JwtUtil jwtUtil;


	private final AmazonS3 s3Client;

	@Value("${cloud.aws.s3.bucket}")
	private String bucketName;

	public CardDto getCard(Long cardId) {
		return new CardDto(cardRepository.findByCardId(cardId).orElseThrow(EntityNotFoundException::new));
	}

	public ResponseEntity<String> createCard(CardDto cardDto, MultipartFile file) throws IOException {
		cardDto.setUsers(userRepository.findByEmail(jwtUtil.getUserEmail()));
		columnsRepository.findById(cardDto.getColumns().getId()).orElseThrow(EntityNotFoundException::new);

		String awsUrl = "";
		if(file != null) {
			awsUrl = uploadFile(file);
			URL url = s3Client.getUrl(bucketName, awsUrl);
			awsUrl = url.toString();
		}
		cardDto.setUrl(awsUrl);
		cardRepository.save(new Card(cardDto));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Transactional
	public ResponseEntity<CardDto> updateCard(Long cardId, CardDto cardDto, MultipartFile file) throws IOException {
		Card card = userValidation(cardId);
		if(file != null){
			String awsUrl = uploadFile(file);
			URL url = s3Client.getUrl(bucketName, awsUrl);
			awsUrl = url.toString();
			card.setUrl(awsUrl);
		}
		card.update(cardDto);
		return ResponseEntity.status(HttpStatus.OK).body(new CardDto(card));
	}

	@Transactional
	public ResponseEntity<String> deleteCard(Long cardId) {
		Card card = userValidation(cardId);
		commentRepository.deleteAllByCardsId(cardId);
		cardRepository.delete(card);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Transactional
	public ResponseEntity<String> moveCard(Long cardId, CardDto cardDto) {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("없는 카드 입니다."));
		card.move(cardDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	private Card userValidation(Long cardId) {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("없는 카드 입니다."));
		if (!card.getUsers().getEmail().equals(jwtUtil.getUserEmail())) {
			throw new IllegalArgumentException("카드를 생성한 사람이 아닙니다.");
		}
		return card;
	}

	public String uploadFile(MultipartFile file) throws IOException {
		String fileName = generateFileName(file);
		try{
			s3Client.putObject(bucketName, fileName, file.getInputStream(),getObjectMetadata(file));
			return fileName;
		} catch (SdkClientException e){
			throw new IOException("Error uploading file to S3", e);
		}
	}

	private ObjectMetadata getObjectMetadata(MultipartFile file) {
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		objectMetadata.setContentLength(file.getSize());
		return objectMetadata;
	}

	// 파일명 설정
	private String generateFileName(MultipartFile file){
		return UUID.randomUUID() + "-" + file.getOriginalFilename();
	}
}