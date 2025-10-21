INSERT INTO books (id, title, author, review, rating, finished_date, genre, `status`, created_at, updated_at)
VALUES
(1, '클린 코드', '로버트 C. 마틴', '가독성과 유지보수성에 대한 명저', 5, '2024-01-15', 'TECHNOLOGY', 'COMPLETED',
 TIMESTAMPADD(DAY, -10, CURRENT_TIMESTAMP()), TIMESTAMPADD(DAY, -10, CURRENT_TIMESTAMP()));