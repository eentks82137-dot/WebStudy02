-- P101000001 상품의 기본 정보와 해당 상품의 제조사 명, 제조사 담당자 명을 조회하세요

select p.prod_id,
       p.prod_name,
       b.buyer_name,
       b.buyer_charger
  from prod p
 inner join buyer b
on ( p.buyer_id = b.buyer_id )
 where p.prod_id = 'P101000001';

-- P10101 제조사의 기본 정보와 해당 제조사와의 거래 품목 (품목코드, 품목 명)조회

select b.*,
       p.prod_id,
       p.prod_name
  from buyer b
  left outer join prod p
on ( p.buyer_id = b.buyer_id )
 where b.buyer_id = 'P10101';


-- 거래 품목이 없는 제조사만 조회
-- -> 제조사 조회, 거래 품목이 없는 제조사를 조건으로

select *
  from buyer
 where buyer_id not in (
   select buyer_id
     from prod
);


-- 회원들 중에서 상품을 한번이라도 구매한 적이 있는 회원을 조회

select *
  from member
 where mem_id in (
   select mem_id
     from cart
);

-- 회원들 중에서 상품을 한번 이상 구매한 적이 있는 회원의 기본 정보와 상품 코드 조회

select *
  from member
natural join cart;


-- 회원정보와 구매코드 조회

select *
  from member m
  left outer join cart c
on ( m.mem_id = c.mem_id )
 where m.mem_id = 'n001';



 -- P101000001 상품의 상품코드, 상품명, 분류코드, 분류명, 해당 상품의 제조사 명, 제조사 담당자 명을 조회하세요

 select p.prod_id, p.prod_name, p.LPROD_GU, lp.LPROD_NAME, b.BUYER_NAME, b.BUYER_CHARGER
 from prod p
 inner join buyer b on (p.buyer_id = b.buyer_id)
 inner join lprod lp on (p.lprod_gu = lp.lprod_gu)
 where p.prod_id = 'P101000001';


 -- P10101 제조사의 기본 정보와 해당 제조사와의 거래 품목 (품목코드, 품목 명, 상품분류명)조회

select b.*, p.prod_id, p.PROD_NAME, lp.LPROD_NAME
from buyer b
left outer join prod p on (b.buyer_id = p.buyer_id)
left outer join lprod lp on (p.LPROD_GU = lp.LPROD_GU)
where b.buyer_id = 'P10103';


select b.*, p2.prod_id, p2.PROD_NAME, p2.LPROD_NAME
from buyer b
left outer join (
    select * 
    from prod p 
    inner join lprod lp on (p.LPROD_GU = lp.LPROD_GU)
    ) p2 on (b.buyer_id = p2.buyer_id)
where b.buyer_id = 'P10103';

WITH a as (
  select * 
    from prod p 
    inner join lprod lp on (p.LPROD_GU = lp.LPROD_GU)
)
select *
from buyer b left outer join a on (b.buyer_id = a.buyer_id)
where b.buyer_id = 'P10101';


-- scalar subquery
-- a001 회원의 기본 정보와 상품 구매 건수 조회



select mem_name, (
    select count(*)
  from cart
  where mem_id = 'a001'
) cnt
from member
where mem_id ='a001';