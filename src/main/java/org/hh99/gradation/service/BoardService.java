package org.hh99.gradation.service;

import java.util.List;

import org.hh99.gradation.aop.DistributedLock;
import org.hh99.gradation.config.RedisConfig;
import org.hh99.gradation.domain.dto.BoardDto;
import org.hh99.gradation.domain.dto.UserDto;
import org.hh99.gradation.domain.entity.Board;
import org.hh99.gradation.domain.entity.BoardUser;
import org.hh99.gradation.domain.entity.User;
import org.hh99.gradation.jwt.JwtUtil;
import org.hh99.gradation.repository.BoardRepository;
import org.hh99.gradation.repository.BoardUserRepository;
import org.hh99.gradation.repository.UserRepository;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardRepository boardRepository;
	private final BoardUserRepository boardUserRepository;
	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;
	private final RedissonClient redissonClient;

	@DistributedLock(key = "#key")
	public void createBoard(BoardDto boardDto) {

		Long userId = jwtUtil.getUserId();
		if(getBoardUserSize(userId) >= 10) {
			throw new IllegalArgumentException("보드의 최대 생성 제한이 넘습니다.");
		}
		User user = findUser(userId);

		Board board = new Board(boardDto, userId);
		BoardUser boardUser = new BoardUser(board, user);

		boardRepository.save(board);
		boardUserRepository.save(boardUser);
	}

	public List<BoardDto> getBoardList() {

		Long userId = jwtUtil.getUserId();
		List<BoardDto> boardDtoList = boardUserRepository.findAllByUserId(userId)
			.stream().map(boardUser -> new BoardDto(boardUser.getBoard())).toList();

		return boardDtoList;
	}

	public BoardDto getBoard(Long boardId) {

		Board board = findBoard(boardId);
		return new BoardDto(board);
	}

	@DistributedLock(key = "#like")
	public void updateBoard(BoardDto boardDto, Long boardId) {

		Long userId = jwtUtil.getUserId();
		Board board = findBoard(boardId);

		if (!checkUser(userId, board.getCreateUserId()))
			throw new IllegalArgumentException("보드를 생성한 사용자가 아닙니다.");
		board.update(boardDto);
	}

	public void deleteBoard(Long boardId) {

		Long userId = jwtUtil.getUserId();
		Board board = findBoard(boardId);

		if (!checkUser(userId, board.getCreateUserId()))
			throw new IllegalArgumentException("보드를 생성한 사용자가 아닙니다.");
		List<BoardUser> boardUserList = boardUserRepository.findAllByBoardId(boardId);

		boardUserRepository.deleteAll(boardUserList);
		boardRepository.delete(board);
	}

	public void inviteBoard(Long boardId, List<UserDto> userlist) {

		Board board = findBoard(boardId);

		for (UserDto userDto : userlist) {
			User user = userRepository.findByEmail(userDto.getEmail());
			BoardUser boardUser = new BoardUser(board, user);

			boardUserRepository.save(boardUser);
		}
	}

	private Board findBoard(Long boardId) {
		return boardRepository.findById(boardId).orElseThrow(() ->
			new IllegalArgumentException("보드를 찾을 수 없습니다."));
	}

	private User findUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(() ->
			new IllegalArgumentException("사용자를 찾을 수 없습니다."));
	}

	private Boolean checkUser(Long userId, Long boardUserId) {
		return userId.equals(boardUserId);
	}

	private int getBoardUserSize(Long userId) {
		List<BoardUser> boardUserList = boardUserRepository.findAllByUserId(userId);
		return boardUserList.size();
	}
}
