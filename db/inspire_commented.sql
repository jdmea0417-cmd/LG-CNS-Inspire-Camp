-- ============================================================================
-- SQL 초보자를 위한 주석 정리 (Day03 ~ Day04 수업 실습 코드)
-- ============================================================================

USE mysql;
USE inspire;

-- Day03: GROUP BY, HAVING, JOIN
-- ============================================================================

-- [개념] 복수행 함수(집계 함수)
-- - 여러 행의 데이터를 입력으로 받아서 하나의 결과값을 반환하는 함수
-- - 예: COUNT(*), SUM(), AVG(), MIN(), MAX()
-- - 주의: SELECT에서 집계함수를 쓰면 일반 컬럼은 함께 사용 불가능
-- - 해결책: GROUP BY를 사용해서 묶을 그룹 컬럼을 지정하면 된다!

-- [문법] GROUP BY { 컬럼명 | 식 | 위치번호 }

-- 1. employee 테이블의 모든 데이터 보기
SELECT 	*
FROM		employee ;

-- 2. 전체 사원 수 세기 (COUNT 함수)
-- COUNT(*): 모든 행의 개수를 세어줌
SELECT	COUNT(*) `사원수`
FROM		employee ;

-- 3. 보너스가 NULL이 아닌 사원 세기
-- IFNULL(): NULL값을 다른 값으로 바꿔서 처리
-- COUNT(IFNULL(BONUS_PCT, 0)): 보너스가 있는 사원만 센다
SELECT	COUNT( IFNULL(BONUS_PCT, 0)) `BONUS`
FROM		employee ;

-- 4. 최저 급여 찾기 (MIN 함수)
-- MIN(): 여러 값 중 가장 작은 값을 반환
SELECT	MIN(SALARY)
FROM		employee ;

-- 5. SUBQUERY를 이용해 최저급여를 받는 사원 찾기
-- SUBQUERY: SELECT 안에 다른 SELECT를 넣는 것
-- 먼저 괄호 안의 SELECT로 최저급여를 구하고,
-- 그 값과 비교해서 해당 사원의 정보를 모두 출력
SELECT	*
FROM		employee
WHERE		SALARY = (	SELECT	MIN(SALARY)
						FROM		employee ) ; 

-- 6. 급여의 총합과 평균 구하기
-- SUM(): 모든 값을 더한 합계를 반환
-- AVG(): 평균값을 반환
SELECT	SUM(SALARY), 
		AVG(SALARY)
FROM		employee ; 

-- 7. 부서별 급여 통계 (GROUP BY 활용)
-- GROUP BY DEPT_ID: 부서별로 그룹을 나눠서 각 부서별 통계를 낸다
-- WHERE는 GROUP BY 전에 필터링 (급여 3000000 이상인 사원만)
-- HAVING은 GROUP BY 후에 필터링 (그룹화된 결과에 조건을 건다)
SELECT	DEPT_ID,
		SUM(SALARY),     -- 부서별 급여총합
		AVG(SALARY),     -- 부서별 급여평균
		COUNT(*)         -- 부서별 사원수
FROM		employee
WHERE		SALARY >= 3000000   -- 먼저 급여 3000000 이상만 선택
GROUP BY DEPT_ID            -- 부서별로 그룹화
HAVING 	SUM(SALARY) >= 4000000 ; -- 그룹화된 결과에서 총합이 4000000 이상인 부서만

-- 8. 부서별 최고급여 조회
-- MAX(): 여러 값 중 가장 큰 값을 반환
SELECT	DEPT_ID, MAX(SALARY)
FROM		employee
GROUP BY DEPT_ID ; 

-- 9. 급여 등급별 인원수 집계 (CASE 문과 GROUP BY 조합)
-- CASE: 조건에 따라 다른 값을 반환하는 IF문 같은 문법
-- WHEN 조건 THEN 값: 조건이 참이면 해당 값을 반환
-- ELSE: 모든 조건에 맞지 않으면 이 값을 반환
SELECT 
   CASE 
      WHEN SALARY <= 3000000 THEN '초급'
      WHEN SALARY <= 4000000 THEN '중급'
      ELSE '고급'
   END AS 급여등급,
   COUNT(*) AS 인원수
FROM 
   EMPLOYEE
GROUP BY 
	CASE 
      WHEN SALARY <= 3000000 THEN '초급'
      WHEN SALARY <= 4000000 THEN '중급'
      ELSE '고급'
   END   
ORDER BY 
    FIELD(급여등급, '초급', '중급', '고급');  -- 순서대로 정렬


USE sqldb ; 

-- 10. buytbl(구매 테이블) 데이터 확인
SELECT	*
FROM		buytbl ; 

-- 11. 사용자별 총 구매액 (가격 × 수량의 합)
SELECT	USERID, SUM(PRICE * AMOUNT)
FROM		buytbl 
GROUP BY USERID ;

-- 12. 사용자별 평균 구매 수량
SELECT	USERID, AVG(AMOUNT)
FROM		buytbl 
GROUP BY USERID ;


-- [개념] 윈도우 함수 (분석 함수)
-- - 일반 그룹함수와 다르게 행을 유지하면서 집계 결과를 추가한다
-- - GROUP BY처럼 그룹화하지 않음 (모든 행이 결과에 나타남)
-- - OVER(PARTITION BY ~ ORDER BY ~) 형태로 사용
-- - PARTITION BY: 그룹을 나누는 기준 (생략 가능)
-- - ORDER BY: 정렬 기준

USE INSPIRE ;

-- 13. 각 사원의 부서 평균급여 표시 (행 유지)
-- AVG(SALARY) OVER(PARTITION BY DEPT_ID): 
-- 부서별로 평균급여를 계산하되, 각 사원의 행은 유지한다
SELECT	EMP_NAME,
		SALARY,
		DEPT_ID,
		AVG(SALARY) OVER(PARTITION BY DEPT_ID) AS `부서평균`
FROM		EMPLOYEE ;


-- 14. 급여순위 (RANK 함수)
-- RANK(): 동일값이면 같은 순위를 부여, 다음 순위는 건너뜀
-- 예) 1등, 1등, 3등, 4등
SELECT	EMP_NAME,
		SALARY,
		RANK() OVER(ORDER BY SALARY DESC) AS `순위`
FROM		EMPLOYEE ;

-- 15. 급여순위 (DENSE_RANK 함수)
-- DENSE_RANK(): 동일값이면 같은 순위, 다음 순위는 연달아 부여
-- 예) 1등, 1등, 2등, 3등
SELECT	EMP_NAME,
		SALARY,
		DENSE_RANK() OVER(ORDER BY SALARY DESC) AS `순위`
FROM		EMPLOYEE ;

-- 16. 급여순위 (ROW_NUMBER 함수)
-- ROW_NUMBER(): 모든 행에 고유한 번호를 부여 (동일값도 다른 번호)
-- 예) 1, 2, 3, 4
SELECT	EMP_NAME,
		SALARY,
		ROW_NUMBER() OVER(ORDER BY SALARY DESC) AS `순위`
FROM		EMPLOYEE ;

-- 17. 부서별 급여순위 (PARTITION BY로 부서별 별도 순위)
-- PARTITION BY DEPT_ID: 각 부서마다 따로 순위를 매긴다
SELECT	EMP_NAME,
		DEPT_ID,
		RANK() OVER(PARTITION BY DEPT_ID ORDER BY SALARY DESC) AS `부서순위`
FROM		EMPLOYEE ;

-- 18. 성별에 따른 평균 급여
-- SUBSTRING(EMP_NO, 8, 1): 직원번호의 8번째 자리 추출 (성별 판단)
-- IF(조건, 참일때, 거짓일때): 조건문
-- ROUND(숫자, 소수점자리): 반올림
SELECT	IF(SUBSTRING(EMP_NO, 8, 1) = 1 , 'MALE', 'FEMALE') AS `GENDER`,
		ROUND(AVG(SALARY),0) AS `AVG`
FROM		EMPLOYEE
GROUP BY	GENDER ;  

-- 19. HAVING 절 (GROUP BY 후 필터링)
-- WHERE: GROUP BY 전에 필터링
-- HAVING: GROUP BY 후에 필터링 (집계함수 결과에 대한 조건)

-- 부서별 급여총합이 9000000 이상인 부서만 조회
SELECT	DEPT_ID, 
		SUM(SALARY)
FROM		EMPLOYEE
GROUP BY DEPT_ID 
HAVING   SUM(SALARY) >= 9000000 ; 

USE sqldb ; 

SELECT	*
FROM		buytbl ; 

-- 20. 사용자별 총 구매액이 100 이상인 사용자만 조회 (HAVING 활용)
SELECT 
	userID,
	SUM(PRICE * AMOUNT)
FROM 
	buytbl
GROUP BY 
	userID
HAVING 
	SUM(PRICE) > 100 ; 
	
-- 21. 계층적 집계 (WITH ROLLUP)
-- WITH ROLLUP: 소계와 총계를 함께 표시
-- 그룹이름별, 사용자별 구매비용을 보고, 총계도 함께 출력
SELECT 
	GROUPNAME,
	USERID,
	SUM(PRICE * AMOUNT)
FROM 
	buytbl
GROUP BY 
	GROUPNAME, USERID WITH ROLLUP ; 

-- 22. ORDER BY 정렬
-- { 컬럼명 | 인덱스번호 | 별칭 } ASC(오름차순) / DESC(내림차순)
-- 부서번호가 50번이거나 부서가 없는 사원의 이름, 급여를 급여 높은 순서로 출력
USE INSPIRE ;

SELECT	EMP_NAME,
		SALARY AS `S`   
FROM		EMPLOYEE
WHERE		DEPT_ID = '50' OR DEPT_ID IS NULL
ORDER BY SALARY DESC;  -- DESC: 내림차순 (높은 순)


-- ============================================================================
-- Day04 추가: 더 많은 함수와 서브쿼리 문제들
-- ============================================================================

-- [Q5] 입학기간이 19년 이상 지난 학생 찾기
-- YEAR(): 날짜에서 연도만 추출
-- SUBSTR(): 문자열에서 특정 부분 추출
-- CAST(): 데이터 타입 변환 (문자를 숫자로, 등등)
SELECT
   	STUDENT_NO
	  ,STUDENT_NAME
FROM 	TB_STUDENT
WHERE YEAR(ENTRANCE_DATE)   -- 입학일에서 연도 추출
      - (
            CASE
               -- 주민번호 8번째 자리가 1,2면 1900년생, 아니면 2000년생
               WHEN SUBSTR(STUDENT_SSN, 8, 1) IN ('1', '2') THEN 1900
               ELSE 2000
            END
            + CAST(SUBSTR(STUDENT_SSN, 1, 2) AS UNSIGNED)  -- 주민번호 앞 2자리는 출생년도
        ) > 19;

-- 같은 의도의 더 간단한 버전
SELECT	STUDENT_NO,
  	STUDENT_NAME
FROM 	TB_STUDENT
WHERE YEAR(ENTRANCE_DATE) 
	- 
	CAST(CONCAT('19',SUBSTRING(STUDENT_SSN, 1, 2)) AS INTEGER) > 19 ; 

-- [Q3] 교수의 나이 계산 (여러 방법)
-- CURDATE(): 오늘 날짜를 반환
SELECT 	YEAR(CURDATE()) ;

-- 방법 1: 연도 차이로 나이 계산
SELECT	PROFESSOR_NAME AS `교수이름`,
		YEAR(CURDATE()) - CAST(CONCAT('19',SUBSTRING(PROFESSOR_SSN, 1, 2)) AS INTEGER) AS `나이`
FROM		tb_professor
WHERE		SUBSTRING(PROFESSOR_SSN, 8, 1) = '1'   -- 남자만 (주민번호 8번째가 1)
ORDER BY 2 ASC ; 

-- 방법 2: TIMESTAMPDIFF로 정확히 계산
-- TIMESTAMPDIFF(단위, 시작날짜, 끝날짜): 두 날짜 사이의 차이
-- STR_TO_DATE(): 문자를 날짜로 변환 ('%Y%m%d' 형식)
SELECT
   PROFESSOR_NAME AS `교수이름`
,  TIMESTAMPDIFF(
      YEAR
   ,  STR_TO_DATE(CONCAT('19', SUBSTR(PROFESSOR_SSN, 1, 6)), '%Y%m%d')
   ,  CURDATE()
   ) AS `나이`
FROM TB_PROFESSOR
WHERE SUBSTR(PROFESSOR_SSN, 8, 1) = '1'
ORDER BY `나이`;

-- [Q13] 학과별 휴학생 수
-- COUNT(CASE WHEN 조건 THEN 값 ELSE NULL END): 조건에 맞는 것만 센다
-- 휴학생 수가 0이어야 하므로, ELSE NULL로 제외된 것은 COUNT되지 않음
SELECT	S.DEPARTMENT_NO `학과코드명`,
		COUNT(CASE 	WHEN S.ABSENCE_YN = 'Y'   -- 휴학 여부가 'Y'면 1로 카운트
					THEN 1 ELSE NULL END) AS `휴학생 수`
FROM		tb_student S 
GROUP BY S.DEPARTMENT_NO ;

-- [Q14] 같은 이름을 가진 학생 찾기
-- HAVING으로 같은 이름이 2명 이상인 경우만 필터링
SELECT	S.STUDENT_NAME,
		COUNT(S.STUDENT_NAME)
FROM		tb_student S 
GROUP BY S.STUDENT_NAME  
HAVING 	COUNT(S.STUDENT_NAME) > 1   -- 이름 개수가 1개 초과 (중복)
ORDER BY S.STUDENT_NAME ASC ; 

-- ============================================================================
-- JOIN (테이블 연결)
-- ============================================================================

-- [개념] JOIN
-- - 두 개 이상의 테이블을 연결해서 데이터를 조회
-- - 공통된 컬럼(보통 ID)을 기준으로 매칭

-- [방법 1] 구식 JOIN 문법 (CROSS JOIN)
-- FROM 테이블1, 테이블2 WHERE 테이블1.ID = 테이블2.ID
-- E.DEPT_ID = D.DEPT_ID: employee 테이블의 부서ID와 department 테이블의 부서ID가 같은 행만 연결
SELECT	E.EMP_NAME,
		D.DEPT_NAME
FROM		employee E, department D
WHERE		E.DEPT_ID = D.DEPT_ID ;

-- [방법 2] LEFT JOIN with USING
-- LEFT JOIN: 왼쪽 테이블의 모든 행을 유지, 오른쪽은 매칭되는 것만
-- USING(공통컬럼): 공통 컬럼명이 같을 때 사용 (더 간단)
SELECT			E.EMP_NAME,
				D.DEPT_NAME
FROM			employee   E
LEFT JOIN		department D USING(DEPT_ID) ; 

-- [방법 3] RIGHT JOIN with ON
-- RIGHT JOIN: 오른쪽 테이블의 모든 행을 유지, 왼쪽은 매칭되는 것만
-- ON(조건식): USING과 달리 더 복잡한 조건도 가능
SELECT			E.EMP_NAME,
				D.DEPT_NAME
FROM			employee   E
RIGHT JOIN		department D ON(E.DEPT_ID = D.DEPT_ID) ; 

-- [방법 4] CROSS JOIN (모든 조합)
-- 조건이 없으면 employee의 모든 행 × department의 모든 행 (카티션 곱)
SELECT	E.EMP_NAME,
		D.DEPT_NAME
FROM		employee   E
JOIN		department D ;

-- [테이블 4개 JOIN]
-- 급여등급 테이블과의 JOIN: BETWEEN 연산자 사용
-- E.SALARY BETWEEN S.LOWEST AND S.HIGHEST: 급여가 범위 내에 있는 것 매칭
SELECT 	E.EMP_NAME,
		E.SALARY,
		S.SLEVEL
FROM		employee 	E
JOIN		sal_grade	S ON( E.SALARY BETWEEN S.LOWEST AND S.HIGHEST ); 

-- [다중 JOIN 예시]
-- 사원 → 부서 → 지역 순서로 연결
-- 조건: 부서명이 '해외'로 시작, 지역명순 정렬
SELECT	EMP_NAME,
		DEPT_NAME,
		LOC_DESCRIBE
FROM		employee 	E
JOIN		department 	D ON(E.DEPT_ID = D.DEPT_ID) 
JOIN		location 	L ON(D.LOC_ID = L.LOCATION_ID) 
WHERE		DEPT_NAME LIKE '해외%'   -- LIKE: 문자패턴 매칭 ('해외'로 시작)
ORDER BY 3 ;   -- 3번째 컬럼(LOC_DESCRIBE)으로 정렬

USE SQLDB;

SELECT	*
FROM		usertbl ; 

SELECT	*
FROM		buytbl ; 

-- [INNER JOIN 예시]
-- 사용자ID가 'JYP'인 유저의 이름과 구매상품 조회
SELECT 		U.name, 
			B.prodName
FROM 		usertbl 	U
INNER JOIN 	buytbl 	B ON (U.userID = B.userID)
WHERE 		U.userID = 'JYP';

-- [여러 컬럼 조회]
-- CONCAT(): 문자열 연결 (휴대폰번호 부분들을 합치기)
-- AS: 컬럼에 별칭(다른 이름) 부여
SELECT 	ut.userID  AS 'ID'
		,ut.name  AS '이름'
		,bt.prodName  AS '품목'
		,ut.addr  AS '주소'
		,CONCAT(ut.mobile1,'-',ut.mobile2) AS '연락처'
FROM 		usertbl 	ut 
JOIN 		buytbl 	bt ON ( ut.userID = bt.userID ) ;

-- [EXISTS 서브쿼리]
-- EXISTS: 서브쿼리 결과가 있는지 없는지 확인 (있으면 TRUE, 없으면 FALSE)
-- 구매이력이 있는 회원만 조회
SELECT	*
FROM		usertbl U
WHERE		EXISTS (	SELECT 	*
					FROM		buytbl B
					WHERE		U.USERID = B.USERID ) ;
	
-- [NOT EXISTS 서브쿼리]
-- 구매이력이 없는 회원만 조회
SELECT	*
FROM		usertbl U
WHERE		NOT EXISTS (	SELECT 	*
						FROM		buytbl B
						WHERE		U.USERID = B.USERID ) ;

-- [LEFT JOIN으로 구매이력 없는 회원 찾기 (다른 방법)]
-- IS NULL: 값이 NULL인지 확인
SELECT		U.USERID,
			U.NAME,
			B.prodName
FROM		USERTBL 	U
LEFT JOIN	BUYTBL 	B ON(U.USERID = B.USERID) 
WHERE		PRODNAME IS NULL ;   -- 매칭되지 않아 NULL인 것 = 구매이력 없음

USE INSPIRE ;

-- [LEFT OUTER JOIN 예시]
-- 부서배치를 받지 않은 (DEPT_ID가 NULL) 사원 찾기
SELECT		EMP_NAME,
			DEPT_NAME
FROM		EMPLOYEE 	E
LEFT JOIN	DEPARTMENT	D ON(E.DEPT_ID = D.DEPT_ID)
WHERE		E.DEPT_ID IS NULL ; 

-- [Self Join (같은 테이블 여러번 JOIN)]
-- 사원의 이름과 해당 사원을 관리하는 상사의 이름, 그리고 상사의 상사 이름 조회
-- E: 사원, M: 직속상사, S: 상사의 상사
SELECT		E.EMP_NAME,
			M.EMP_NAME,
			S.EMP_NAME
FROM		employee 	E
LEFT JOIN	employee 	M	ON(E.MGR_ID = M.EMP_ID)   -- 직속상사 찾기
LEFT JOIN	employee 	S	ON(M.MGR_ID = S.EMP_ID) ; -- 그 상사의 상사 찾기

-- [JOIN 최종 예시 - 6개 테이블 JOIN]
-- 사원 → 부서 → 직급 → 급여등급 → 지역 → 국가 모두 연결
SELECT 
      E.EMP_NAME
      , D.DEPT_NAME
      , J.JOB_TITLE
      , S.SLEVEL
      , L.LOC_DESCRIBE
      , C.COUNTRY_NAME
FROM
      employee   	E
JOIN
      department 	D ON(E.DEPT_ID = D.DEPT_ID)
JOIN
      JOB 			J ON(E.JOB_ID = J.JOB_ID)      
JOIN
      sal_grade 	S ON(E.SALARY BETWEEN S.LOWEST AND S.HIGHEST ) 	
JOIN
      location   	L ON(D.LOC_ID = L.LOCATION_ID)
JOIN
      country 		C ON(L.COUNTRY_ID = C.COUNTRY_ID)	
WHERE	
		JOB_TITLE = '대리' AND LOC_DESCRIBE LIKE '아시아%'
ORDER BY  
      3 DESC ;

-- ============================================================================
-- SUBQUERY (서브쿼리) - SELECT 안의 SELECT
-- ============================================================================

-- [개념]
-- - 유형: 단일행(결과 1개), 다중행(결과 여러개)
-- - 사용영역: SELECT(스칼라), FROM(인라인뷰), WHERE/HAVING(일반 서브쿼리)
-- - 연산자: 단일행(=, <>, <, >), 다중행(IN, ANY, ALL, EXISTS)

-- [예시] 나승원 사원과 같은 부서원을 조회

-- 단계1: 나승원의 부서ID 먼저 찾기
SELECT	DEPT_ID
FROM		EMPLOYEE 
WHERE		EMP_NAME = '나승원';

-- 단계2: 부서ID = '50'인 모든 사원 찾기
SELECT	*
FROM		EMPLOYEE 
WHERE		DEPT_ID = '50' ; 

-- 단계1, 2를 하나로 (서브쿼리)
-- 괄호 안의 SELECT(부서ID)를 먼저 실행, 그 결과로 WHERE 조건 처리
SELECT	*
FROM		EMPLOYEE 
WHERE		DEPT_ID =  (SELECT	DEPT_ID
						FROM		EMPLOYEE 
						WHERE		EMP_NAME = '나승원');  

-- [Q] 나승원과 같은 직급이면서 같은 급여를 받는 사원 찾기
SELECT 	*
FROM 		employee
WHERE 	JOB_ID = (	SELECT JOB_ID
					FROM employee
					WHERE EMP_NAME = '나승원') 
AND 		SALARY = (	SELECT SALARY
					FROM employee
					WHERE EMP_NAME = '나승원') ; 

-- 위의 더 효율적인 방법 (다중열 서브쿼리)
-- (JOB_ID, SALARY)를 한 번에 비교
SELECT 	*
FROM 		employee
WHERE 	(JOB_ID, SALARY) = (	SELECT JOB_ID, SALARY
								FROM employee
								WHERE EMP_NAME = '나승원') 

-- [집계함수 중첩 불가 문제 & 해결]
-- 부서별 급여총합이 가장 많은 부서 조회

-- ❌ 틀린 방법 (집계함수 중첩 불가)
-- MAX(SUM(...))는 불가능!
SELECT	MAX(SUM(SALARY))
FROM		employee
GROUP BY DEPT_ID ; 

-- ✅ 올바른 방법1: 서브쿼리로 먼저 집계한 후, 그 결과에 MAX
SELECT 	DEPT_NAME,
		MAX(급여총합)
FROM     (	
			SELECT DEPT_ID, SUM(salary) AS '급여총합'
			from employee 
			GROUP BY dept_id
		)  VIEW   -- VIEW: 임시 테이블 별칭
JOIN		department D ON( VIEW.DEPT_ID = D.DEPT_ID );

-- ✅ 올바른 방법2: 같은 내용 (반복)
SELECT 	DEPT_NAME,
		MAX(급여총합)
FROM     (	
			SELECT DEPT_ID, SUM(salary) AS '급여총합'
			from employee 
			GROUP BY dept_id
		)  VIEW 
JOIN		department D ON( VIEW.DEPT_ID = D.DEPT_ID );

-- [Q] 최저급여를 받는 사원의 모든 정보 조회
-- 앞에서 본 기본 서브쿼리 예제
SELECT	*
FROM		employee
WHERE		SALARY = (	SELECT	MIN(SALARY)
						FROM		employee ) ;
