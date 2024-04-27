-- --관리자 테이블--
insert into admin_tb(email, password, brand_name, role, phone, address, business_num, update_at, created_at)
values ('Ropa@naver.com', '1234', 'Ropa', 'BRAND', '010-1111-1111', '서울특별시 강남구', '827-546-7895', NULL, now()),
       ('Ace@naver.com', '1234', 'Ace', 'BRAND', '010-2222-2222', '서울특별시 종로구', '737-546-7196', NULL, now()),
       ('BB@naver.com', '1234', 'BB', 'BRAND', '010-3333-3333','서울특별시 강동구', '657-546-2897', NULL, now()),
       ('Admin@naver.com', '1234', null, 'ADMIN', '010-4444-4444', '부산광역시 해운대구', '1234', NULL, now());

INSERT INTO user_tb (email, password, nick_name, my_name, address, mobile, height, weight, blue_checked)
VALUES ('user1@example.com', '1234', '사용자1의 별명', '사용자1의 실명', '서울특별시 강남구', '010-1234-5678', '175cm', '70kg', FALSE),
       ('user2@example.com', '1234', '사용자2의 별명', '사용자2의 실명', '경기도 분당구', '010-9876-5432', '168cm', '60kg', TRUE),
       ('user3@example.com', '1234', '사용자3의 별명', '사용자3의 실명', '인천광역시 남동구', '010-5555-5555', '180cm', '75kg', TRUE);

-- 아이템(Items) 더미 데이터 삽입
INSERT INTO items_tb (admin_id, name, description, size, price, dis_count_price, stock)
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

-- 배송 주소(DeliveryAddress) 더미 데이터 삽입
INSERT INTO delivery_address_tb (user_id, recipient, postal_code, address, address_detail, phone_number)
VALUES
    (1, '수령인1', '12345', '서울특별시 강남구', '테헤란로 123길', '010-1111-1111'),
    (2, '수령인2', '54321', '경기도 분당구', '판교로 456번길', '010-2222-2222'),
    (3, '수령인3', '98765', '인천광역시 남동구', '구월로 789번길', '010-3333-3333');

-- 배송(Delivery) 더미 데이터 삽입
INSERT INTO delivery_tb (delivery_address_id, status, start_date, end_date)
VALUES
    (1, '배송중', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (2, '배송완료', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, '배송중', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- 주문(Order) 더미 데이터 삽입
INSERT INTO order_tb (user_id, delivery_id, total_order_amount, fee, order_date)
VALUES
    (1, 1, 50000, 22.000, CURRENT_TIMESTAMP),
    (2, 2, 75000, 22.000, CURRENT_TIMESTAMP),
    (1, 3, 100000, 22.000, CURRENT_TIMESTAMP);

-- OrderHistory 더미 데이터 삽입
INSERT INTO orderhistory_tb (order_id, order_items_id, admin_id, total_quantity, total_price, free)
VALUES
    -- OrderHistory 1
    (1, 1, 1, 2, 100000, 22.000),
    -- OrderHistory 2
    (2, 2, 2, 1, 75000, 22.000),
    -- OrderHistory 3
    (1, 3, 1, 3, 150000, 22.000),
    -- OrderHistory 4
    (2, 4, 2, 2, 100000, 22.000),
    -- OrderHistory 5
    (3, 5, 1, 1, 50000, 22.000);