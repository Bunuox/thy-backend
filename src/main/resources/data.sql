
INSERT INTO TBL_USER (id, username, email, password) VALUES
('550e8400-e29b-41d4-a716-446655440000', 'john_doe', 'john.doe@example.com', 'password123'),
('550e8400-e29b-41d4-a716-446655440001', 'jane_smith', 'jane.smith@example.com', 'password456'),
('550e8400-e29b-41d4-a716-446655440002', 'alice_jones', 'alice.jones@example.com', 'password789'),
('550e8400-e29b-41d4-a716-446655440003', 'bob_brown', 'bob.brown@example.com', 'password321');

INSERT INTO TBL_NOTE (id, user_id, title, description, created_at, updated_at, due_date, status) VALUES
('550e8400-e29b-41d4-a716-446655440100', '550e8400-e29b-41d4-a716-446655440000', 'Shopping List', 'Buy groceries for the week', '2024-09-20', '2024-09-21', '2024-09-25', 'P'),
('550e8400-e29b-41d4-a716-446655440101', '550e8400-e29b-41d4-a716-446655440001', 'Project Deadline', 'Complete the project documentation', '2024-09-22', '2024-09-23', '2024-09-30', 'O'),
('550e8400-e29b-41d4-a716-446655440102', '550e8400-e29b-41d4-a716-446655440002', 'Meeting Notes', 'Notes from the team meeting', '2024-09-18', '2024-09-19', '2024-09-20', 'C'),
('550e8400-e29b-41d4-a716-446655440103', '550e8400-e29b-41d4-a716-446655440003', 'Birthday Party', 'Plan the birthday party for Saturday', '2024-09-15', '2024-09-16', '2024-09-28', 'P');