-- --관리자 테이블--
insert into admin_tb(email, password, brand_name, role, phone, address, business_num, update_at, created_at)
values ('nike@naver.com', '1234', 'nike', 'BRAND', '010-1111-1111', '서울특별시 강남구', '827-546-7895', NULL, now()),
       ('Ace@naver.com', '1234', 'Ace', 'BRAND', '010-2222-2222', '서울특별시 종로구', '737-546-7196', NULL, now()),
       ('BB@naver.com', '1234', 'BB', 'BRAND', '010-3333-3333', '서울특별시 강동구', '657-546-2897', NULL, now()),
       ('Admin@naver.com', '1234', null, 'ADMIN', '010-4444-4444', '부산광역시 해운대구', '1234', NULL, now());

INSERT INTO user_tb (email, password, nick_name, my_name, address, mobile, height, weight, instagram, status, blue_checked)
VALUES ('user1@example.com', '1234', '팀장', '김성훈', '서울특별시 강남구', '010-1234-5678', '175cm', '70kg', '@instagram', '신청 전', FALSE),
       ('user2@example.com', '1234', '부팀장', '김주혁', '경기도 분당구', '010-9876-5432', '168cm', '60kg', '@twitter', '승인 대기', FALSE),
       ('user3@example.com', '1234', '실장', '박선규', '인천광역시 남동구', '010-5555-5555', '180cm', '75kg', '@facebook', '승인', TRUE);

-- 아이템(Items) 더미 데이터 삽입
INSERT INTO items_tb (admin_id, name, description, size, price, discount_price, stock)
VALUES
    -- 아이템 1부터 10
    (1, '아이템1', '이 아이템은 아주 좋습니다.', 'M', '50000', 45000, '100'),
    (2, '아이템2', '이 아이템은 더 좋습니다.', 'L', '75000', NULL, '50'),
    (3, '아이템3', '이 아이템은 제일 좋습니다.', 'XL', '100000', 90000, '200'),
    (1, '아이템4', '이 아이템은 아주 좋습니다.', 'M', '50000', 45000, '100'),
    (2, '아이템5', '이 아이템은 더 좋습니다.', 'L', '75000', NULL, '50'),
    (3, '아이템6', '이 아이템은 제일 좋습니다.', 'XL', '100000', 90000, '200'),
    (1, '아이템7', '이 아이템은 아주 좋습니다.', 'M', '50000', 45000, '100'),
    (2, '아이템8', '이 아이템은 더 좋습니다.', 'L', '75000', NULL, '50'),
    (3, '아이템9', '이 아이템은 제일 좋습니다.', 'XL', '100000', 90000, '200'),
    (1, '아이템10', '이 아이템은 아주 좋습니다.', 'M', '50000', 45000, '100'),
    (2, '아이템11', '이 아이템은 더 좋습니다.', 'L', '75000', NULL, '50'),
    (3, '아이템12', '이 아이템은 제일 좋습니다.', 'XL', '100000', 90000, '200'),
    (1, '아이템13', '이 아이템은 아주 좋습니다.', 'M', '50000', 45000, '100'),
    (2, '아이템14', '이 아이템은 더 좋습니다.', 'L', '75000', NULL, '50'),
    (3, '아이템15', '이 아이템은 제일 좋습니다.', 'XL', '100000', 90000, '200'),
    (1, '아이템16', '이 아이템은 아주 좋습니다.', 'M', '50000', 45000, '100'),
    (2, '아이템17', '이 아이템은 더 좋습니다.', 'L', '75000', NULL, '50'),
    (3, '아이템18', '이 아이템은 제일 좋습니다.', 'XL', '100000', 90000, '200'),
    (1, '아이템19', '이 아이템은 아주 좋습니다.', 'M', '50000', 45000, '100'),
    (2, '아이템20', '이 아이템은 더 좋습니다.', 'L', '75000', NULL, '50');

-- 카테고리 테이블 더미
INSERT INTO category_tb (main, sub, items_id) VALUES
                                                  ('상의', '셔츠', 1),
                                                  ('상의', '셔츠', 2),
                                                  ('상의', '맨투맨', 3),
                                                  ('상의', '맨투맨', 4),
                                                  ('하의', '슬랙스', 5),
                                                  ('하의', '슬랙스', 6),
                                                  ('하의', '슬랙스', 7),
                                                  ('하의', '반바지', 8),
                                                  ('하의', '반바지', 9),
                                                  ('상의', '맨투맨', 10),
                                                  ('상의', '셔츠', 11),
                                                  ('상의', '맨투맨', 12),
                                                  ('하의', '슬랙스', 13),
                                                  ('상의', '맨투맨', 14),
                                                  ('하의', '반바지', 15),
                                                  ('하의', '슬랙스', 16),
                                                  ('상의', '셔츠', 17),
                                                  ('하의', '반바지', 18),
                                                  ('상의', '셔츠', 19),
                                                  ('상의', '남방', 20);



-- 배송 주소(DeliveryAddress) 더미 데이터 삽입
INSERT INTO delivery_address_tb (user_id, recipient, postal_code, address, address_detail, phone_number)
VALUES (1, '수령인1', '12345', '서울특별시 강남구', '테헤란로 123길', '010-1111-1111'),
       (2, '수령인2', '54321', '경기도 분당구', '판교로 456번길', '010-2222-2222'),
       (3, '수령인3', '98765', '인천광역시 남동구', '구월로 789번길', '010-3333-3333');

-- 배송(Delivery) 더미 데이터 삽입
INSERT INTO delivery_tb (delivery_address_id, status, start_date, end_date)
VALUES (1, '배송중', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (2, '배송완료', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (3, '배송중', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO inquiry_tb (status, title, content, comment, user_id, admin_id, commented_at, created_at)
VALUES (true, '상품 문의', '상품이 반팔도 셔츠도 입고 되면 좋겠는데  혹시 안 되나요?.', '출시 예정 제품 있습니다! 1월 27일 11:00부로 상품 구매 가능합니다!', 3, 1,
        '2024-01-26 11:30:00', '2024-01-25 11:30:00'),
       (true, '배송 문의', '주문한 상품이 아직 배송되지 않았습니다.', '평균 배송 2~3일 걸립니다!', 2, 1, '2024-02-18 09:00:00',
        '2024-02-16 01:00:00'),
       (false, '상품 문의', '배송중 상품 교환이 가능한가요?', '', 2, 1, null, NOW()),
       (false, '상품 문의', '반팔 카라티 모델이 차은우인가요?', '', 1, 1, null, NOW()),
       (false, '상품 문의', '저희 사이즈는 프리사이즈 밖에 없나요?', '', 1, 2, null, NOW());

-- 주문(Order) 더미 데이터 삽입
INSERT INTO order_tb (user_id, delivery_id, total_order_amount, fee, order_date)
VALUES (1, 1,  50000,  5000, CURRENT_TIMESTAMP),
       (2, 2,  75000,  7500, CURRENT_TIMESTAMP),
       (3, 3, 100000, 10000, CURRENT_TIMESTAMP);

-- 주문 상세(OrderHistory) 더미 데이터 삽입
INSERT INTO order_history_tb (admin_id, order_id, items_id, order_item_qty, order_item_price, fee)
VALUES (1, 2, 1, 2, 100000, 10000),
       (2, 3, 2, 1,  75000, 7500),
       (1, 1, 1, 3, 300000, 30000),
       (3, 2, 3, 2, 150000, 15000),
       (2, 1, 2, 4, 225000, 22500),
       (3, 3, 3, 2, 200000, 20000),
       (1, 1, 1, 1,  50000, 5000),
       (2, 2, 2, 3,  75000, 7500),
       (1, 3, 1, 4, 400000, 40000),
       (3, 1, 3, 2, 200000, 20000);

-- -- 주문(Order) 더미 데이터 삽입
-- INSERT INTO order_tb (user_id, delivery_id, total_order_amount, fee, order_date)
-- VALUES
--     (1, 1, 50000, 22.000, CURRENT_TIMESTAMP),
--     (2, 2, 75000, 22.000, CURRENT_TIMESTAMP),
--     (1, 3, 100000, 22.000, CURRENT_TIMESTAMP);

-- -- OrderHistory 더미 데이터 삽입
-- INSERT INTO orderhistory_tb (order_id, order_items_id, admin_id, total_quantity, total_price, fee)
-- VALUES
--     -- OrderHistory 1
--     (1, 1, 1, 2, 100000, 22.000),
--     -- OrderHistory 2
--     (2, 2, 2, 1, 75000, 22.000),
--     -- OrderHistory 3
--     (1, 3, 1, 3, 150000, 22.000),
--     -- OrderHistory 4
--     (2, 4, 2, 2, 100000, 22.000),
--     -- OrderHistory 5
--     (3, 5, 1, 1, 50000, 22.000);