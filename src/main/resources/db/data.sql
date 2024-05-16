-- --관리자 테이블--
insert into admin_tb(email, password, brand_name, role, phone, address, business_num, update_at, created_at)
values ('salomon@naver.com', '1234', 'SALOMON', 'BRAND', '010-1111-1111', '서울특별시 강남구', '827-546-7895', NULL, now()),
       ('lee@naver.com', '1234', 'lee', 'BRAND', '010-2222-2222', '서울특별시 종로구', '737-546-7196', NULL, now()),
       ('espionage@naver.com', '1234', 'espionage', 'BRAND', '010-3333-3333', '서울특별시 강동구', '657-546-2897', NULL, now()),
       ('Admin@naver.com', '1234', 'RopaAdmin', 'ADMIN', '010-4444-4444', '부산광역시 해운대구', '1234', NULL, now());


-- 앱 사용자 더미
INSERT INTO user_tb (email, password, nick_name, my_name, address, mobile, height, weight, job, intro_msg, instagram,
                     status, blue_checked, mileage)
VALUES ('junghein@example.com', '1234', 'junghein', '정해인', '서울특별시 강남구', '010-1234-5678', '175cm', '70kg', '직장인',
        '어깨 넓은 보통 체형',
        'holyhaein', '신청 전', FALSE, 2000),
       ('limsiwan@example.com', '1234', 'limsiwan', '임시완', '경기도 분당구', '010-9876-5432', '168cm', '56kg', '학생',
        '키작고 마른 체형', 'yim_siwang',
        '승인 대기', FALSE, 0),
       ('bunwuseok@example.com', '1234', 'bunwuseok', '변우석', '인천광역시 남동구', '010-5555-5555', '180cm', '75kg', '직장인',
        '연예인 체형', 'byeonwooseok',
        '승인', TRUE, 3000),
       ('gd@example.com', '1234', 'gd', '김지용', '인천광역시 남동구', '010-5555-5555', '180cm', '70kg', '학생',
        '키크고 마른 체형', 'xxxibgdrgn',
        '신청 전', FALSE, 0),
       ('kimsuhyun@example.com', '1234', 'kimsuhyun', '김수현', '인천광역시 남동구', '010-5555-5555', '180cm', '85kg', '학생',
        '키크고 덩치가 있는 체형', 'soohyun_k216',
        '신청 전', FALSE, 5000);

-- 카테고리 테이블 더미
INSERT INTO category_tb (main, sub)
VALUES ('상의', '반팔'),
       ('하의', '청바지'),
       ('상의', '반팔 셔츠'),
       ('하의', '카고 팬츠'),
       ('상의', '니트 집업'),
       ('하의', '청바지'),
       ('상의', '니트'),
       ('하의', '청바지'),
       ('상의', '청 셔츠'),
       ('상의', '맨투맨'),
       ('상의', '셔츠'),
       ('상의', '맨투맨'),
       ('하의', '슬랙스'),
       ('상의', '맨투맨'),
       ('하의', '반바지'),
       ('하의', '슬랙스'),
       ('상의', '셔츠'),
       ('하의', '반바지'),
       ('상의', '셔츠'),
       ('상의', '남방');

-- 아이템(Items) 더미 데이터 삽입
INSERT INTO items_tb (admin_id, name, description, size, price, discount_price, stock, category_id, status)
VALUES
    (1, 'SCRAPPED 티셔츠(WHITE)', '힙하고 유니크한 반팔로 어느 코디에도 잘 어울립니다.', 'M', 45000, null, 100, 1, true),
    (2, 'scratch 블루 청바지', '봄, 여름, 가을 까지 계절 상관없이 힙한 룩에 잘어울리는 청바지입니다.', 'L', 32000, NULL, 50, 2, true),
    (3, 'CHAIN STITCH OPEN COLLAR HALF SHIRT_BLACK', '빈티지 무드의 자수 그래픽이 사용된 오픈 카라 셔츠입니다.', 'XL', 55000, null, 200, 3, true),
    (1, '나일론 립스탑 유틸리티 팬츠_BLACK', '현대식 디자인에 맞춘 카고 팬츠입니다.', 'M', 50000, null, 100, 4, true),
    (2, 'crop cable sweater', '방모 원사임에도 모달이 섞여 기분좋은 찰랑거림이 있는게 매력적입니다.', 'L', 75000, NULL, 50, 5, true),
    (3, '라이트블루 데님팬츠', '셀비지 원단으로 만들어진 라이트 불루 데님.', 'XL', 80000, null, 200, 6, true),
    (1, 'Bulky wool full zip knit', '고급스러운 컬러감을 선택하여 단품으로도 한겨울에 이너로도 활용도가 높은 아이템.', 'L', 59000, null, 100, 7, true),
    (2, 'Two Tuck Wide Black Jeans', '투턱 디테일과 사선 포켓 활용으로 입체적인 핏 감의 네츄럴한 무드 연출.', 'L', 32000, NULL, 50, 8, true),
    (3, '데님 셔츠', 'Basic하면서 유니크한 디자인으로 기본으로 입어도 좋고 이너로 입어도 잘 어울리는 셔츠입니다.', 'L', 43000, null, 200, 9, true),
    (1, '아이템10', '이 아이템은 아주 좋습니다.', 'M', 50000, null, 100, 10, true),
    (2, '아이템11', '이 아이템은 더 좋습니다.', 'L', 75000, NULL, 50, 11, true),
    (3, '아이템12', '이 아이템은 제일 좋습니다.', 'XL', 100000, null, 200, 12, true),
    (1, '아이템13', '이 아이템은 아주 좋습니다.', 'M', 50000, null, 100, 13, true),
    (2, '아이템14', '이 아이템은 더 좋습니다.', 'L', 75000, NULL, 50, 14, true),
    (3, '아이템15', '이 아이템은 제일 좋습니다.', 'XL', 100000, null, 200, 15, true),
    (1, '아이템16', '이 아이템은 아주 좋습니다.', 'M', 50000, null, 100, 16, true),
    (2, '아이템17', '이 아이템은 더 좋습니다.', 'L', 75000, NULL, 50, 17, true),
    (3, '아이템18', '이 아이템은 제일 좋습니다.', 'XL', 100000, null, 200, 18, true),
    (1, '아이템19', '이 아이템은 아주 좋습니다.', 'M', 50000, null, 100, 19, true),
    (2, '아이템20', '이 아이템은 더 좋습니다.', 'L', 75000, NULL, 50, 20, true);

-- 장바구니 더미데이터
INSERT INTO cart_tb (user_id, items_id, quantity, total_amount)
VALUES (1, 15, 2, 200000),
       (1, 16, 1, 100000),
       (2, 3, 3, 300000),
       (2, 4, 1, 50000),
       (2, 5, 1, 75000),
       (2, 6, 1, 100000),
       (2, 7, 1, 50000),
       (2, 8, 1, 75000),
       (2, 9, 1, 100000),
       (2, 10, 1, 50000);

-- 코디 테이블 더미
insert into codi_tb (user_id, title, description, created_at)
values (1, '바캉스룩', '여름 바캉스 갈때 입기 좋은 룩입니다.', NOW()),
       (1, '시크한 대학 선배 룩', '힙하게 이미지가 강렬하게 보이고 싶을 때 추천드리는 룩입니다.', NOW()),
       (3, '잘생긴 연하남룩', '톤온톤 매치로 보편적이지 않은 매치지만 기본 디자인으로 꾸안꾸 느낌의 룩입니다.', NOW()),
       (3, '집 앞 꾸안꾸 룩', '집 앞 카페에 갈 때 꾸민듯 안꾸민듯의 정석인 룩입니다.', NOW()),
       (2, '코디5', '좋은 코디입니다.', NOW()),
       (2, '코디6', '좋은 코디입니다.', NOW()),
       (2, '코디7', '좋은 코디입니다.', NOW()),
       (1, '코디8', '좋은 코디입니다.', NOW()),
       (1, '코디9', '좋은 코디입니다.', NOW()),
       (1, '코디10', '좋은 코디입니다.', NOW()),
       (4, '코디11', '좋은 코디입니다.', NOW()),
       (4, '코디12', '좋은 코디입니다.', NOW()),
       (4, '코디13', '좋은 코디입니다.', NOW());


-- 코디랑 아이템을 연결해주는 테이블
insert into codi_items_tb (items_id, codi_id)
values (16, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 3),
       (6, 3),
       (7, 4),
       (8, 4);

-- 사진 테이블 더미
insert into photo_tb (uuid_name, original_file_name, path, sort, admin_id, user_id, codi_id, items_id, created_at,
                      is_main_photo)
values ('uuid_브랜드사진1', 'salomon', '/upload/brand/salomon.jpg', 'BRAND', 1, null, null, null, NOW(), true),
       ('uuid_브랜드사진2', 'lee', '/upload/brand/lee.png', 'BRAND', 2, null, null, null, NOW(), true),
       ('uuid_브랜드사진3', 'espionage', '/upload/brand/espionage.png', 'BRAND', 3, null, null, null, NOW(), true),
       ('uuid_사용자사진1', '사용자사진1', '/upload/user/user1.webp', 'USER', null, 1, null, null, NOW(), true),
       ('uuid_사용자사진2', '사용자사진2', '/upload/user/user2.webp', 'USER', null, 2, null, null, NOW(), true),
       ('uuid_사용자사진3', '사용자사진3', '/upload/user/user3.webp', 'USER', null, 3, null, null, NOW(), true),
       ('uuid_사용자사진4', '사용자사진4', '/upload/user/user4.webp', 'USER', null, 4, null, null, NOW(), true),
       ('uuid_사용자사진5', '사용자사진5', '/upload/user/user5.webp', 'USER', null, 5, null, null, NOW(), true),
       ('uuid_코디사진1', '코디사진1', '/upload/codi/codi-1.jpg', 'CODI', null, null, 1,
        null, NOW(), true),
       ('uuid_코디사진2', '코디사진2', '/upload/codi/codi-1-1.jpg', 'CODI', null, null, 1,
        null, NOW(), false),
       ('uuid_코디사진3', '코디사진3', '/upload/codi/codi-2.jpg', 'CODI', null, null, 2,
        null, NOW(), true),
       ('uuid_코디사진4', '코디사진4', '/upload/codi/codi-2-1.jpg', 'CODI', null, null, 2, null, NOW(), false),
       ('uuid_코디사진5', '코디사진5', '/upload/codi/codi-3.webp', 'CODI', null, null, 3, null, NOW(), true),
       ('uuid_코디사진6', '코디사진6', '/upload/codi/codi-3-1.webp', 'CODI', null, null, 3, null, NOW(), false),
       ('uuid_코디사진7', '코디사진7', '/upload/codi/codi-4.webp', 'CODI', null, null, 4, null, NOW(), true),
       ('uuid_코디사진8', '코디사진8', '/upload/codi/codi-4-1.webp', 'CODI', null, null, 4, null, NOW(), false),
       ('uuid_아이템사진1', '아이템사진1', '/upload/items/item01/mainItemPhoto.jpg', 'ITEM', null, null, null, 1, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진1', '/upload/items/item01/detail01.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진1', '/upload/items/item01/detail02.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진1', '/upload/items/item01/detail03.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진1', '/upload/items/item01/detail04.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진2', '아이템사진2', '/upload/items/item02/mainItemPhoto.jpg', 'ITEM', null, null, null, 2, NOW(), true),
       ('uuid_아이템사진2서브', '아이템사진서브사진2', '/upload/items/item02/detail01.jpg', 'ITEM', null, null, null, 2, NOW(), false),
       ('uuid_아이템사진2서브', '아이템사진서브사진2', '/upload/items/item02/detail02.jpg', 'ITEM', null, null, null, 2, NOW(), false),
       ('uuid_아이템사진2서브', '아이템사진서브사진2', '/upload/items/item02/detail03.jpg', 'ITEM', null, null, null, 2, NOW(), false),
       ('uuid_아이템사진2서브', '아이템사진서브사진2', '/upload/items/item02/detail04.jpg', 'ITEM', null, null, null, 2, NOW(), false),
       ('uuid_아이템사진3', '아이템사진사진3', '/upload/items/item03/mainItemPhoto.jpg', 'ITEM', null, null, null, 3, NOW(), true),
       ('uuid_아이템사진3서브', '아이템사진서브사진3', '/upload/items/item03/detail01.jpg', 'ITEM', null, null, null, 3, NOW(), false),
       ('uuid_아이템사진3서브', '아이템사진서브사진3', '/upload/items/item03/detail02.jpg', 'ITEM', null, null, null, 3, NOW(), false),
       ('uuid_아이템사진4', '아이템사진사진4', '/upload/items/item04/mainItemPhoto.jpg', 'ITEM', null, null, null, 4, NOW(), true),
       ('uuid_아이템사진4서브', '아이템사진서브사진4', '/upload/items/item04/detail01.jpg', 'ITEM', null, null, null, 4, NOW(), false),
       ('uuid_아이템사진4서브', '아이템사진서브사진4', '/upload/items/item04/detail02.jpg', 'ITEM', null, null, null, 4, NOW(), false),
       ('uuid_아이템사진5', '아이템사진사진5', '/upload/items/item05/mainItemPhoto.jpg', 'ITEM', null, null, null, 5, NOW(), true),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail01.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail02.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진6', '아이템사진사진6', '/upload/items/item06/mainItemPhoto.jpg', 'ITEM', null, null, null, 6, NOW(), true),
       ('uuid_아이템사진6서브', '아이템사진서브사진6', '/upload/items/item06/detail01.jpg', 'ITEM', null, null, null, 6, NOW(), false),
       ('uuid_아이템사진6서브', '아이템사진서브사진6', '/upload/items/item06/detail02.jpg', 'ITEM', null, null, null, 6, NOW(), false),
       ('uuid_아이템사진6서브', '아이템사진서브사진6', '/upload/items/item06/detail03.jpg', 'ITEM', null, null, null, 6, NOW(), false),
       ('uuid_아이템사진6서브', '아이템사진서브사진6', '/upload/items/item06/detail04.jpg', 'ITEM', null, null, null, 6, NOW(), false),
       ('uuid_아이템사진6서브', '아이템사진서브사진6', '/upload/items/item06/detail05.jpg', 'ITEM', null, null, null, 6, NOW(), false),
       ('uuid_아이템사진7', '아이템사진사진7', '/upload/items/item07/mainItemPhoto.jpg', 'ITEM', null, null, null, 7, NOW(), true),
       ('uuid_아이템사진7서브', '아이템사진서브사진7', '/upload/items/item07/detail01.jpg', 'ITEM', null, null, null, 7, NOW(), false),
       ('uuid_아이템사진7서브', '아이템사진서브사진7', '/upload/items/item07/detail02.jpg', 'ITEM', null, null, null, 7, NOW(), false),
       ('uuid_아이템사진8', '아이템사진사진8', '/upload/items/item08/mainItemPhoto.jpg', 'ITEM', null, null, null, 8, NOW(), true),
       ('uuid_아이템사진8서브', '아이템사진서브사진8', '/upload/items/item08/detail01.jpg', 'ITEM', null, null, null, 8, NOW(), false),
       ('uuid_아이템사진8서브', '아이템사진서브사진8', '/upload/items/item08/detail02.jpg', 'ITEM', null, null, null, 8, NOW(), false),
       ('uuid_아이템사진8서브', '아이템사진서브사진8', '/upload/items/item08/detail03.jpg', 'ITEM', null, null, null, 8, NOW(), false),
       ('uuid_아이템사진9', '아이템사진사진9', '/upload/items/item09/mainItemPhoto.jpg', 'ITEM', null, null, null, 19, NOW(), true),
       ('uuid_아이템사진9서브', '아이템사진서브사진9', '/upload/items/item09/detail01.jpg', 'ITEM', null, null, null, 19, NOW(), false),
       ('uuid_아이템사진9서브', '아이템사진서브사진9', '/upload/items/item09/detail02.jpg', 'ITEM', null, null, null, 19, NOW(), false),
       ('uuid_아이템사진9서브', '아이템사진서브사진9', '/upload/items/item09/detail03.jpg', 'ITEM', null, null, null, 19, NOW(), false),
       ('uuid_아이템사진9서브', '아이템사진서브사진9', '/upload/items/item09/detail04.jpg', 'ITEM', null, null, null, 19, NOW(), false);


-- 좋아요 기능
insert into love_tb (user_id, codi_id, is_loved)
values (2, 1, true),
       (3, 2, true),
       (4, 3, true),
       (3, 4, true),
       (5, 6, true),
       (4, 5, true),
       (3, 7, true),
       (4, 8, true),
       (5, 9, true),
       (4, 2, true),
       (3, 3, true),
       (5, 4, true),
       (5, 1, true),
       (3, 10, true),
       (4, 11, true),
       (3, 12, true),
       (2, 13, true);

-- 배송(Delivery) 더미 데이터 삽입
INSERT INTO delivery_tb (status, start_date, end_date, recipient, postal_code, address, address_detail, phone_number)
VALUES ('배송중', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '수령인1', '12345', '서울특별시 강남구', '테헤란로 123길', '010-1111-1111'),
       ('배송완료', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '수령인2', '54321', '경기도 분당구', '판교로 456번길', '010-2222-2222'),
       ('배송중', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '수령인3', '98765', '인천광역시 남동구', '구월로 789번길', '010-3333-3333');

-- 주문(Order) 더미 데이터 삽입
INSERT INTO order_tb (user_id, delivery_id, purchase_amount, fee, order_date, delivery_type, pay_method)
VALUES (1, 1, 50000, 5000, CURRENT_TIMESTAMP, 'FREE', 'CREDIT'),
       (2, 2, 75000, 7500, CURRENT_TIMESTAMP, 'FREE', 'CREDIT'),
       (3, 3, 100000, 10000, CURRENT_TIMESTAMP, 'FREE', 'CREDIT');


INSERT INTO inquiry_tb (status, title, content, comment, user_id, admin_id, commented_at, created_at)
VALUES (true, '상품 문의', '상품이 반팔도 셔츠도 입고 되면 좋겠는데  혹시 안 되나요?.', '출시 예정 제품 있습니다! 1월 27일 11:00부로 상품 구매 가능합니다!', 1, 1,
        '2024-01-26 11:30:00', '2024-01-25 11:30:00'),
       (true, '배송 문의', '주문한 상품이 아직 배송되지 않았습니다.', '평균 배송 2~3일 걸립니다!', 1, 1, '2024-02-18 09:00:00',
        '2024-02-16 01:00:00'),
       (false, '상품 문의', '배송중 상품 교환이 가능한가요?', '', 1, 1, null, NOW()),
       (false, '상품 문의', '반팔 카라티 모델이 차은우인가요?', '', 3, 2, null, NOW()),
       (false, '옷 사이즈 질문드립니다', '총 기장과 가슴 폭좀 알려주세요', '', 2, 2, null, NOW()),
       (false, '혀가 거짓말을 하면?', '전혀 아니에요..', '', 4, 1, null, NOW()),
       (false, '스님이 공중부양 하면?', '어중이 떠중이', '', 5, 1, null, NOW()),
       (false, '여자 : 좋은 소식과 나쁜 소식이 있어. 우리 헤어지자.', '남자 : 그럼 나쁜 소식은?', '', 1, 3, null, NOW()),
       (false, '상품 문의', '저희 사이즈는 프리사이즈 밖에 없나요?', '', 3, 2, null, NOW());

-- 주문 상세(OrderHistory) 더미 데이터 삽입
INSERT INTO order_history_tb (admin_id, order_id, items_id, order_item_qty, order_item_price, fee)
VALUES (1, 2, 1, 2, 90000, 9000),
       (2, 3, 2, 1, 32000, 3200),
       (1, 1, 1, 3, 135000, 13500),
       (3, 2, 3, 2, 110000, 11000),
       (2, 1, 5, 8, 600000, 60000),
       (3, 3, 3, 2, 110000, 11000),
       (1, 1, 6, 1, 80000, 8000),
       (2, 2, 2, 3, 96000, 9600),
       (1, 3, 7, 4, 236000, 23600),
       (3, 1, 10, 2, 100000, 10000);