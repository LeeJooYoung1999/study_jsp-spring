DROP TABLE IF EXISTS tbl_board_attachment;
CREATE TABLE tbl_board_attachment (
	 no INTEGER AUTO_INCREMENT PRIMARY KEY,
	 filename VARCHAR(256) NOT NULL,
	 path VARCHAR(256) NOT NULL,
	 content_type VARCHAR(56),
	 size INTEGER,
	 bno INTEGER NOT NULL,
	 reg_date DATETIME DEFAULT now(),
     CONSTRAINT FOREIGN KEY(bno) REFERENCES tbl_board(no)
 );
select * from tbl_board_attachment;