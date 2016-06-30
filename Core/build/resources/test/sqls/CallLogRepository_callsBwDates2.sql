INSERT INTO  person(
            id, name, phone_no, address)
    VALUES (12, 'CS', '95382', 'KK Pudur');
@@
INSERT INTO  call_log(
            call_log_id, id, party_phone_no, call_type, start_time, end_time, 
            location)
    VALUES (109,12,'15','IN','2016-06-03 00:00:00','2016-06-03 00:10:00', 'BANGALORE');
    @@
    INSERT INTO  call_log(
            call_log_id, id, party_phone_no, call_type, start_time, end_time, 
            location)
    VALUES (113,12,'15','IN','2016-06-03 00:20:00','2016-06-03 01:00:00', 'BANGALORE');
    @@
    INSERT INTO  call_log(
            call_log_id, id, party_phone_no, call_type, start_time, end_time, 
            location)
    VALUES (114,12,'15','OUT','2016-06-04 01:20:00','2016-06-04 01:30:00', 'HYDERABAD');
    @@
    INSERT INTO  call_log(
            call_log_id, id, party_phone_no, call_type, start_time, end_time, 
            location)
    VALUES (110,12,'16','IN','2016-06-03 12:00:00','2016-06-03 00:10:00', 'HYDERABAD');
    @@
    INSERT INTO  call_log(
            call_log_id, id, party_phone_no, call_type, start_time, end_time, 
            location)
    VALUES (111,12,'17','OUT','2016-06-04 01:20:00.231','2016-06-04 01:30:00', 'BANGALORE');
    @@
    INSERT INTO  call_log(
            call_log_id, id, party_phone_no, call_type, start_time, end_time, 
            location)
    VALUES (112,12,'18','IN','2016-06-04 01:00:00','2016-06-04 02:01:00', 'PUNE');
@@