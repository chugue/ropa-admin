-- --관리자 테이블--
insert into admin_tb(email, password, brand_name, role, phone, address, business_num, update_at, created_at)
values ('nike@naver.com', '1234', 'nike', 'BRAND', '010-1111-1111', '서울특별시 강남구', '827-546-7895', NULL, now()),
       ('Ace@naver.com', '1234', 'Ace', 'BRAND', '010-2222-2222', '서울특별시 종로구', '737-546-7196', NULL, now()),
       ('BB@naver.com', '1234', 'BB', 'BRAND', '010-3333-3333', '서울특별시 강동구', '657-546-2897', NULL, now()),
       ('Admin@naver.com', '1234', 'RopaAdmin', 'ADMIN', '010-4444-4444', '부산광역시 해운대구', '1234', NULL, now());


-- 앱 사용자 더미
INSERT INTO user_tb (email, password, nick_name, my_name, address, mobile, height, weight, job, intro_msg, instagram,
                     status, blue_checked, mileage)
VALUES ('user1@example.com', '1234', '사용자1의 별명', '사용자1의 실명', '서울특별시 강남구', '010-1234-5678', '175cm', '70kg', '직장인',
        '어깨 넓은 보통 체형',
        '@instagram', '신청 전', FALSE, 2000),
       ('user2@example.com', '1234', '사용자2의 별명', '사용자2의 실명', '경기도 분당구', '010-9876-5432', '168cm', '56kg', '학생',
        '키작고 마른 체형', 'wdohwan',
        '승인 대기', FALSE, 0),
       ('user3@example.com', '1234', '사용자3의 별명', '사용자3의 실명', '인천광역시 남동구', '010-5555-5555', '180cm', '75kg', '직장인',
        '연예인 체형', '@facebook',
        '승인', TRUE, 3000),
       ('user4@example.com', '1234', '사용자4의 별명', '사용자4의 실명', '인천광역시 남동구', '010-5555-5555', '180cm', '70kg', '학생',
        '키크고 마른 체형', '@facebook',
        '신청 전', FALSE, 0),
       ('user5@example.com', '1234', '사용자5의 별명', '사용자5의 실명', '인천광역시 남동구', '010-5555-5555', '180cm', '85kg', '학생',
        '키크고 덩치가 있는 체형', '@facebook',
        '신청 전', FALSE, 5000);

-- 카테고리 테이블 더미
INSERT INTO category_tb (main, sub)
VALUES ('상의', '셔츠'),
       ('상의', '셔츠'),
       ('상의', '맨투맨'),
       ('상의', '맨투맨'),
       ('하의', '슬랙스'),
       ('하의', '슬랙스'),
       ('하의', '슬랙스'),
       ('하의', '반바지'),
       ('하의', '반바지'),
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
    -- 아이템 1부터 10
    (1, 'SCRAPPED 티셔츠(WHITE)', '이 아이템은 아주 좋습니다.', 'M', 50000, null, 100, 1, true),
    (2, '아이템2', '이 아이템은 더 좋습니다.', 'L', 75000, NULL, 50, 2, true),
    (3, '아이템3', '이 아이템은 제일 좋습니다.', 'XL', 100000, null, 200, 3, true),
    (1, '아이템4', '이 아이템은 아주 좋습니다.', 'M', 50000, null, 100, 4, true),
    (2, '아이템5', '이 아이템은 더 좋습니다.', 'L', 75000, NULL, 50, 5, true),
    (3, '아이템6', '이 아이템은 제일 좋습니다.', 'XL', 100000, null, 200, 6, true),
    (1, '아이템7', '이 아이템은 아주 좋습니다.', 'M', 50000, null, 100, 7, true),
    (2, '아이템8', '이 아이템은 더 좋습니다.', 'L', 75000, NULL, 50, 8, true),
    (3, '아이템9', '이 아이템은 제일 좋습니다.', 'XL', 100000, null, 200, 9, true),
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
VALUES (1, 16, 2, 200000),
       (1, 19, 1, 100000),
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
values (1, '코디1', '좋은 코디입니다.', NOW()),
       (1, '코디2', '좋은 코디입니다.', NOW()),
       (3, '코디3', '좋은 코디입니다.', NOW()),
       (3, '코디4', '좋은 코디입니다.', NOW()),
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
values (1, 1),
       (2, 1),
       (3, 1),
       (2, 2),
       (3, 2),
       (6, 2),
       (5, 3),
       (7, 3),
       (10, 3);

-- 사진 테이블 더미
insert into photo_tb (uuid_name, original_file_name, path, sort, admin_id, user_id, codi_id, items_id, created_at,
                      is_main_photo)
values ('uuid_브랜드사진1', '브랜드사진1', '/upload/brand/brand1.jpg', 'BRAND', 1, null, null, null, NOW(), true),
       ('uuid_브랜드사진2', '브랜드사진2', '/upload/brand/brand2.jpeg', 'BRAND', 2, null, null, null, NOW(), true),
       ('uuid_브랜드사진3', '브랜드사진3', '/upload/brand/brand3.jpeg', 'BRAND', 3, null, null, null, NOW(), true),
       ('uuid_사용자사진1', '사용자사진1', '/upload/user/user1.webp', 'USER', null, 1, null, null, NOW(), true),
       ('uuid_사용자사진2', '사용자사진2', '/upload/user/user2.webp', 'USER', null, 2, null, null, NOW(), true),
       ('uuid_사용자사진3', '사용자사진3', '/upload/user/user3.webp', 'USER', null, 3, null, null, NOW(), true),
       ('uuid_사용자사진4', '사용자사진4', '/upload/user/user4.webp', 'USER', null, 4, null, null, NOW(), true),
       ('uuid_사용자사진5', '사용자사진5', '/upload/user/user5.webp', 'USER', null, 5, null, null, NOW(), true),
       ('uuid_아이템사진2', '아이템사진2', '/upload/items/item02/mainItemPhoto.jpg', 'ITEM', null, null, null, 2, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item02/detail01.jpg', 'ITEM', null, null, null, 2, NOW(), false),
       ('uuid_아이템사진4', '아이템사진3', '/upload/items/item03/mainItemPhoto.jpg', 'ITEM', null, null, null, 3, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진3', '/upload/items/item03/detail01.jpg', 'ITEM', null, null, null, 3, NOW(), false),
       ('uuid_아이템사진5', '아이템사진4', '/upload/items/item04/mainItemPhoto.jpg', 'ITEM', null, null, null, 4, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진4', '/upload/items/item04/detail01.jpg', 'ITEM', null, null, null, 4, NOW(), false),
       ('uuid_아이템사진6', '아이템사진5', '/upload/items/item05/mainItemPhoto.jpg', 'ITEM', null, null, null, 5, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진5', '/upload/items/item05/detail01.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진7', '아이템사진6', '/upload/items/item6.webp', 'ITEM', null, null, null, 6, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진6', '/upload/items/item6.webp', 'ITEM', null, null, null, 6, NOW(), false),
       ('uuid_아이템사진8', '아이템사진7', '/upload/items/item7.webp', 'ITEM', null, null, null, 7, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진7', '/upload/items/item7.webp', 'ITEM', null, null, null, 7, NOW(), false),
       ('uuid_아이템사진9', '아이템사진8', '/upload/items/item8.webp', 'ITEM', null, null, null, 8, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진8', '/upload/items/item8.webp', 'ITEM', null, null, null, 8, NOW(), false),
       ('uuid_아이템사진0', '아이템사진9', '/upload/items/item9.webp', 'ITEM', null, null, null, 9, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진9', '/upload/items/item9.webp', 'ITEM', null, null, null, 9, NOW(), false),
       ('uuid_아이템사진10', '아이템사진10', '/upload/items/item10.webp', 'ITEM', null, null, null, 10, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진10', '/upload/items/item10.webp', 'ITEM', null, null, null, 10, NOW(), false),
       ('uuid_아이템사진11', '아이템사진11', '/upload/items/item11.webp', 'ITEM', null, null, null, 11, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진11', '/upload/items/item11.webp', 'ITEM', null, null, null, 11, NOW(), false),
       ('uuid_아이템사진12', '아이템사진12', '/upload/items/item12.webp', 'ITEM', null, null, null, 12, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진12', '/upload/items/item12.webp', 'ITEM', null, null, null, 12, NOW(), false),
       ('uuid_아이템사진13', '아이템사진13', '/upload/items/item13.webp', 'ITEM', null, null, null, 13, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진13', '/upload/items/item13.webp', 'ITEM', null, null, null, 13, NOW(), false),
       ('uuid_아이템사진14', '아이템사진14', '/upload/items/item14.webp', 'ITEM', null, null, null, 14, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진14', '/upload/items/item14.webp', 'ITEM', null, null, null, 14, NOW(), false),
       ('uuid_아이템사진15', '아이템사진15', '/upload/items/item15.webp', 'ITEM', null, null, null, 15, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진15', '/upload/items/item15.webp', 'ITEM', null, null, null, 15, NOW(), false),
       ('uuid_아이템사진16', '아이템사진16', '/upload/items/item16.webp', 'ITEM', null, null, null, 16, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진16', '/upload/items/item16.webp', 'ITEM', null, null, null, 16, NOW(), false),
       ('uuid_아이템사진17', '아이템사진17', '/upload/items/item17.webp', 'ITEM', null, null, null, 17, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진17', '/upload/items/item17.webp', 'ITEM', null, null, null, 17, NOW(), false),
       ('uuid_아이템사진18', '아이템사진18', '/upload/items/item18.webp', 'ITEM', null, null, null, 18, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진18', '/upload/items/item18.webp', 'ITEM', null, null, null, 18, NOW(), false),
       ('uuid_코디사진1', '코디사진1', '/upload/codi/2ffec590-2404-4d86-aab6-f39905f20091_코디사진1.jpg', 'CODI', null, null, 1,
        null, NOW(), true),
       ('uuid_코디사진2', '코디사진2', '/upload/codi/64c9cd13-dc34-416d-9c60-bc18c9da5e17_코디사진2.jpg', 'CODI', null, null, 1,
        null, NOW(), false),
       ('uuid_코디사진3', '코디사진3', '/upload/codi/f34bbd93-71ed-4758-a76d-4aaaffe59abd_코디사진3.jpg', 'CODI', null, null, 1,
        null, NOW(), false),
       ('uuid_코디사진4', '코디사진4', '/upload/codi/codi-1.webp', 'CODI', null, null, 2, null, NOW(), true),
       ('uuid_코디사진5', '코디사진5', '/upload/codi/codi-1.webp', 'CODI', null, null, 2, null, NOW(), false),
       ('uuid_코디사진6', '코디사진6', '/upload/codi/codi-2.webp', 'CODI', null, null, 3, null, NOW(), true),
       ('uuid_코디사진7', '코디사진7', '/upload/codi/codi-1.webp', 'CODI', null, null, 3, null, NOW(), false),
       ('uuid_코디사진8', '코디사진8', '/upload/codi/codi-3.webp', 'CODI', null, null, 4, null, NOW(), true),
       ('uuid_코디사진9', '코디사진9', '/upload/codi/codi-4.webp', 'CODI', null, null, 5, null, NOW(), true),
       ('uuid_코디사진10', '코디사진10', '/upload/codi/codi-5.webp', 'CODI', null, null, 6, null, NOW(), true),
       ('uuid_코디사진11', '코디사진11', '/upload/codi/codi-6.webp', 'CODI', null, null, 7, null, NOW(), true),
       ('uuid_코디사진12', '코디사진12', '/upload/codi/codi-7.webp', 'CODI', null, null, 8, null, NOW(), true),
       ('uuid_코디사진13', '코디사진13', '/upload/codi/codi-8.webp', 'CODI', null, null, 9, null, NOW(), true),
       ('uuid_코디사진14', '코디사진14', '/upload/codi/codi-9.webp', 'CODI', null, null, 10, null, NOW(), true),
       ('uuid_코디사진15', '코디사진15', '/upload/codi/codi-10.webp', 'CODI', null, null, 11, null, NOW(), true),
       ('uuid_코디사진16', '코디사진16', '/upload/codi/codi-11.webp', 'CODI', null, null, 12, null, NOW(), true),
       ('uuid_코디사진17', '코디사진17', '/upload/codi/codi-12.webp', 'CODI', null, null, 13, null, NOW(), true),
       ('uuid_관리자사진', '관리자사진', '/upload/brand/admin.png', 'BRAND', 4, null, null, null, NOW(), true),
       ('uuid_아이템사진1', '아이템사진1', '/upload/items/item01/mainItemPhoto.jpg', 'ITEM', null, null, null, 1, NOW(), true),
       ('uuid_아이템사진1서브', '아이템서브사진1', '/upload/items/item01/detail01.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail02.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail03.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail04.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail05.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail06.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail07.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail08.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail09.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진1서브', '아이템서브사진2', '/upload/items/item01/detail10.jpg', 'ITEM', null, null, null, 1, NOW(), false),
       ('uuid_아이템사진2', '아이템사진2', '/upload/items/item02/mainItemPhoto.jpg', 'ITEM', null, null, null, 2, NOW(), true),
       ('uuid_아이템사진2서브', '아이템사진서브사진2', '/upload/items/item02/detail01.jpg', 'ITEM', null, null, null, 2, NOW(), false),
       ('uuid_아이템사진2서브', '아이템사진서브사진2', '/upload/items/item02/detail02.jpg', 'ITEM', null, null, null, 2, NOW(), false),
       ('uuid_아이템사진2서브', '아이템사진서브사진2', '/upload/items/item02/detail03.jpg', 'ITEM', null, null, null, 2, NOW(), false),
       ('uuid_아이템사진3', '아이템사진사진3', '/upload/items/item03/mainItemPhoto.jpg', 'ITEM', null, null, null, 3, NOW(), true),
       ('uuid_아이템사진3서브', '아이템사진서브사진3', '/upload/items/item03/detail01.jpg', 'ITEM', null, null, null, 3, NOW(), false),
       ('uuid_아이템사진3서브', '아이템사진서브사진3', '/upload/items/item03/detail02.jpg', 'ITEM', null, null, null, 3, NOW(), false),
       ('uuid_아이템사진4', '아이템사진사진4', '/upload/items/item04/mainItemPhoto.jpg', 'ITEM', null, null, null, 4, NOW(), true),
       ('uuid_아이템사진4서브', '아이템사진서브사진4', '/upload/items/item04/detail01.jpg', 'ITEM', null, null, null, 4, NOW(), false),
       ('uuid_아이템사진4서브', '아이템사진서브사진4', '/upload/items/item04/detail02.jpg', 'ITEM', null, null, null, 4, NOW(), false),
       ('uuid_아이템사진5', '아이템사진사진5', '/upload/items/item05/mainItemPhoto.jpg', 'ITEM', null, null, null, 5, NOW(), true),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail01.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail02.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail03.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail04.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail05.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail06.jpg', 'ITEM', null, null, null, 5, NOW(), false),
       ('uuid_아이템사진5서브', '아이템사진서브사진5', '/upload/items/item05/detail07.jpg', 'ITEM', null, null, null, 5, NOW(), false);


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


INSERT INTO order_tb (user_id, delivery_id, purchase_amount, fee, order_date, delivery_type, pay_method)
VALUES (1, 1, 50000, 5000, CURRENT_TIMESTAMP, 'FREE', 'CREDIT'),
       (2, 2, 75000, 7500, CURRENT_TIMESTAMP, 'FREE', 'CREDIT'),
       (3, 3, 100000, 10000, CURRENT_TIMESTAMP, 'FREE', 'CREDIT');

-- 주문(Order) 더미 데이터 삽입
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
VALUES (1, 2, 1, 2, 100000, 10000),
       (2, 3, 2, 1, 75000, 7500),
       (1, 1, 1, 3, 300000, 30000),
       (3, 2, 3, 2, 150000, 15000),
       (2, 1, 5, 8, 225000, 22500),
       (3, 3, 3, 2, 200000, 20000),
       (1, 1, 6, 1, 50000, 5000),
       (2, 2, 2, 3, 75000, 7500),
       (1, 3, 7, 4, 400000, 40000),
       (3, 1, 10, 2, 200000, 20000);