USE mysql;
USE inspire;

/*
select : 데이터를 검색할 때 사용하는 문법

SELECT 	column_name | * | 표현식이나 함수를 포함하는 식 | [as] 별칭 | distinct
FROM 		table-name ;
[WHERE]		-- 행의 제한
[GROUP BY]	-- 하위데이터 그룹 생성
[HAVING]		-- 그룹에 대한 조건
[ORDER BY]	-- 정렬(ASC, DESC)

컬럼명은 대소문자를 구별하지 않는다.
키워드는 대문자로 작성하는게 원칙
*/
SELECT 	emp_id,
			emp_name
FROM		employee;

/*
별칭 사용시 공백, 특수문자, 숫자를 포함할 경우 ``으로
*/
SELECT 	emp_id AS `사원 번호`,
			emp_name AS `사원 이름`
FROM		employee;

/*
NULL 치환할 수 있는 함수가 있지않을까?
*/
SELECT	EMP_NAME,
			SALARY,
			SALARY * 12 + IFNULL(BONUS_PCT, 0) AS `연봉`
FROM 		employee;

SELECT IFNULL('하이', '넌 누구냐?'), NULLIF(NULL, '널 이구나');

-- DISTINCT : 컬럼에 포함된 중복값을 한번씩만 출력할 때
-- SELECT절에 한번만
SELECT 	DISTINCT	DEPT_ID, JOB_ID
FROM 		employee;

SELECT 	*
FROM		sal_grade;

SELECT 	*
FROM		country;

-- 부모의 기본 키와 자식의 외래 키가 다를 수 있다.
SELECT 	*
FROM		location;

SELECT 	*
FROM		department;

-- Q) 90번 부서의 모든 사원정보를 검색한다면?
-- where (제한 행)

SELECT	*
FROM		employee
WHERE 	DEPT_ID = '90';

SELECT	*
FROM		employee
WHERE 	JOB_ID = 'j2';