document.addEventListener('DOMContentLoaded', function () {

    var docElem = document.documentElement;
    var kanban = document.querySelector('.kanban-demo');
    var board = kanban.querySelector('.board');
    var itemContainers = Array.prototype.slice.call(kanban.querySelectorAll('.board-column-content'));
    var columnGrids = [];
    var dragCounter = 0;
    var boardGrid;

    itemContainers.forEach(function (container) {

        var muuri = new Muuri(container, {
            items: '.board-item',
            layoutDuration: 400,
            layoutEasing: 'ease',
            dragEnabled: true,
            dragSortInterval: 0,
            dragSortGroup: 'column',
            dragSortWith: 'column',
            dragContainer: document.body,
            dragReleaseDuration: 400,
            dragReleaseEasing: 'ease'
        })
            .on('dragStart', function (item) {
                ++dragCounter;
                docElem.classList.add('dragging');
                item.getElement().style.width = item.getWidth() + 'px';
                item.getElement().style.height = item.getHeight() + 'px';
            })
            .on('dragEnd', function (item) {
                if (--dragCounter < 1) {
                    docElem.classList.remove('dragging');
                }
            })
            .on('dragReleaseEnd', function (item) {
                item.getElement().style.width = '';
                item.getElement().style.height = '';
                columnGrids.forEach(function (muuri) {
                    muuri.refreshItems();
                });

                muuri.synchronize();
                let sort_index = 1;
                const cardlist = item._element.parentNode.childNodes;

                (async () => {
                    for (let card of cardlist) {
                        if (card.nodeName != "#text") {
                            await orderFetch(card, sort_index);
                            sort_index++;
                        }
                    }
                })();

                function orderFetch(card, sort_index) {
                    if(card.nodeName != "#text") {
                        const columnsId = card.parentNode.parentNode.getAttribute("id")
                        const cardId = card.firstChild.nextSibling.getAttribute("id")
                        fetch('/api/cards/' + cardId + "/move", {
                            method: 'PATCH',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify({
                                "cardOrder":sort_index,
                                "columns" : {
                                    id : columnsId
                                }
                            }),
                        })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                    }
                }



            })
            .on('layoutStart', function () {
                boardGrid.refreshItems().layout();
            });
        columnGrids.push(muuri);
    });

    boardGrid = new Muuri(board, {
        layoutDuration: 400,
        layoutEasing: 'ease',
        dragEnabled: true,
        dragSortInterval: 0,
        dragStartPredicate: {
            handle: '.board-column-header'
        },
        dragReleaseDuration: 400,
        dragReleaseEasing: 'ease'
    });

    boardGrid.on('dragReleaseEnd', function (item) {
        boardGrid.synchronize();
        // 순서 바뀔시에 순서 저장

        let sort_index = 1;
        const columnList = document.querySelector(".board.muuri").childNodes;

        (async () => {
            for (let column of columnList) {
                console.log(column);
                if (column.nodeName != "#text") {
                    await orderFetch(column, sort_index);
                    sort_index++;
                }
            }
        })();

        function orderFetch(el, sort_index) {
            if(el.nodeName != "#text") {
                const columnId = el.getAttribute('id');
                fetch('/api/user/columns/' + columnId + "/order", {
                    method: 'PATCH',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        "columnsOrder":sort_index
                    }),
                })
                .catch(error => {
                    console.error('Error:', error);
                });
            }
        }

    })
});