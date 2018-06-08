DELIMITER //

DROP procedure IF EXISTS procdemo1//
DROP procedure IF EXISTS procdemo2//

CREATE PROCEDURE procdemo1(
    IN inArg INT
  , OUT outArg INT
  , INOUT varcharArg VARCHAR(80)
  , INOUT dateArg DATETIME)
COMMENT 'stored procedure call demo1'
LANGUAGE SQL
DETERMINISTIC CONTAINS SQL
BEGIN
   SET outArg = inArg + 1;
   SET varcharArg = UPPER(varcharArg);
   SET dateArg = CAST(CAST(dateArg AS DATE) AS DATETIME);
   SELECT dateArg AS 'current_time';
END;
//

SHOW WARNINGS//

CREATE PROCEDURE procdemo2(IN id INT, IN description VARCHAR(200))
COMMENT 'stored procedure call demo2'
LANGUAGE SQL
DETERMINISTIC MODIFIES SQL DATA
BEGIN

    UPDATE memo
       SET memo.description = CONCAT(memo.description, ' + ', description)
         , memo.done = true
         , memo.updated = NOW()
     WHERE memo.id = id;

    SELECT SLEEP(8);

END;
//
SHOW WARNINGS//

DELIMITER ;
