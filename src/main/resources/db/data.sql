-- --관리자 테이블--
insert into admin_tb(email, password, brand_name, role, address, business_num, update_at, created_at)
values ('Ropa@naver.com', '1234', 'Ropa', 'BRAND', '서울특별시 강남구', '827-546-7895', NULL, now()),
       ('Ace@naver.com', '1234', 'Ace', 'BRAND', '서울특별시 종로구', '737-546-7196', NULL, now()),
       ('BB@naver.com', '1234', 'BB', 'BRAND', '서울특별시 강동구', '657-546-2897', NULL, now()),
       ('Admin@naver.com', '1234', null, 'ADMIN', '부산광역시 해운대구', '1234', NULL, now());

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

-- 주문 상세(OrderDetail) 더미 데이터 삽입
INSERT INTO orderdetail_tb (items_id, admin_id, total_quantity, total_price)
VALUES
    (1, 1, 2, 100000),
    (2, 2, 1, 75000),
    (3, 3, 3, 300000);

-- 주문(Order) 더미 데이터 삽입
INSERT INTO order_tb (user_id, order_detail_id, delivery_id, total_order_amount, order_date)
VALUES
    (1, 1, 1, 50000, CURRENT_TIMESTAMP),
    (2, 1, 2, 75000, CURRENT_TIMESTAMP),
    (1, 2, 3, 100000, CURRENT_TIMESTAMP);




-- INSERT INTO delivery_address_tb (user_id, recipient, postal_code, address, address_detail, phone_number)
-- VALUES
--     (1, '서강준', '135-080', '서울시 강남구 역삼동 123번지', '101호', '010-1234-5678'),
--     (2, '우도환', '48058', '부산시 해운대구 우동 456번지', '202호', '010-9876-5432'),
--     (3, '이철수', '700-600', '대구시 중구 삼덕동 789번지', '303호', '010-8765-4321'),
--     (4, '신예은', '215-400', '인천시 남동구 구월동 101번지', '204호', '010-0123-4567'),
--     (5, '설인아', '215-400', '인천시 남동구 구월동 101번지', '205호', '010-3215-4795');


-- -- 배송 정보 더미 데이터 삽입
-- INSERT INTO delivery_tb (delivery_address_id, status, startDate, endDate)
-- VALUES (1, '배송중', '2024-04-27 10:00:00', '2024-04-28 10:00:00'),
--        (2, '배송완료', '2024-04-26 15:30:00', '2024-04-27 15:30:00'),
--        (3, '배송준비중', '2024-04-25 09:45:00', NULL),
--        (4, '배송중', '2024-04-24 12:00:00', NULL),
--        (5, '배송완료', '2024-04-23 18:20:00', '2024-04-24 18:20:00'),
--        (6, '배송준비중', '2024-04-22 11:30:00', NULL),
--        (7, '배송중', '2024-04-21 14:45:00', '2024-04-22 14:45:00'),
--        (8, '배송완료', '2024-04-20 08:00:00', '2024-04-21 08:00:00'),
--        (9, '배송중', '2024-04-19 17:10:00', NULL),
--        (10, '배송준비중', '2024-04-18 21:00:00', NULL);

-- 주문 더미 데이터 삽입
-- INSERT INTO order_tb (delivery_id, user_id, total_order_amount, order_date)
-- VALUES (1, 1, 2500, NOW()),
--        (2, 2, 1800, NOW()),
--        (3, 3, 3200, NOW()),
--        (4, 4, 1500, NOW()),
--        (5, 5, 2800, NOW()),
--        (6, 6, 2100, NOW()),
--        (7, 7, 1900, NOW()),
--        (8, 8, 3500, NOW()),
--        (9, 9, 2700, NOW()),
--        (10, 10, 2300, NOW());


-- --
-- -- --
-- --
-- -- --사용자 테이블--
-- -- INSERT INTO `user_tb` (myName, nickName, photo, height, weight, address, mobile, email, blueChecked, updatedAt,
-- --                        createdAt, photo_id)
-- -- VALUES ('서강준', 'gangjun', 'hong.jpg', '175 cm', '70 kg', '서울시 강남구 역삼동 123번지', '010-1234-5678', 'gangjun@naver.com',
-- --         false, 2024 - 04 - 20 10:15:00, 2023 - 01 - 15 08:30:00, null),
-- --        ('김영희', 'younghee', 'young.jpg', '163 cm', '60 kg', '부산시 해운대구 우동 456번지', '010-9876-5432', 'younghee@naver.com',
-- --         false, 2024 - 04 - 20 09:30:00, 2023 - 01 - 16 08:30:00, null),
-- --        ('이철수', 'chulsoo', 'chul.jpg', '180 cm', '80 kg', '대구시 중구 삼덕동 789번지', '010-8765-4321', 'chulsoo@naver.com',
-- --         false, 2024 - 04 - 19 15:45:00, 2023 - 01 - 17 08:30:00, null),
-- --        ('신예은', 'yeeun', 'yeeun.jpg', '168 cm', '55 kg', '인천시 남동구 구월동 101번지', '010-0123-4567', 'yeeun@naver.com', false,
-- --         2024 - 04 - 18 11:20:00, 2023 - 01 - 18 08:30:00, null),
-- --        ('설인아', 'ina', 'ina.jpg', '164 cm', '52 kg', '인천시 남동구 구월동 101번지', '010-3215-4795', 'ina@naver.com', false,
-- --         2024 - 04 - 18 11:20:00, 2023 - 01 - 19 08:30:00, null);
-- --
-- --
--
