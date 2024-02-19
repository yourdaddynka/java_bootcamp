WITH user_chatrooms AS (SELECT u.User_ID,
                               array_agg(DISTINCT cr.Chatroom_ID)   AS created_rooms_ids,
                               array_agg(DISTINCT cr.Chatroom_Name) AS created_rooms_names,
                               array_agg(DISTINCT mr.Chatroom_ID)   AS rooms_ids,
                               array_agg(DISTINCT mr.Chatroom_Name) AS rooms_names,
                               array_agg(DISTINCT o.User_ID)        AS owners_ids,
                               array_agg(DISTINCT o.Login)          AS owners_logins,
                               array_agg(DISTINCT o.Password)       AS owners_passwords
                        FROM Users u
                                 LEFT JOIN Chatroom cr ON u.User_ID = cr.id_User
                                 LEFT JOIN Message m ON u.User_ID = m.id_User
                                 LEFT JOIN Chatroom mr ON m.id_Chatroom = mr.Chatroom_ID
                                 LEFT JOIN Users o ON mr.id_User = o.User_ID
                        GROUP BY u.User_ID)
SELECT u.*,
       uc.created_rooms_ids,
       uc.created_rooms_names,
       uc.rooms_ids,
       uc.rooms_names,
       uc.owners_ids,
       uc.owners_logins,
       uc.owners_passwords
FROM Users u
         LEFT JOIN user_chatrooms uc ON u.User_ID = uc.User_ID;