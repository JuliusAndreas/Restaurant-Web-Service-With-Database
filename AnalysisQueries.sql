-- best selling food query --
select foodname, sum(quantity) as sells
from reservation r
         join food f on f.id = r.food_id
group by foodname
ORDER BY sells DESC;

-- most money-making food --
select f.foodname, sum(r.quantity * f.price) as moneyMade
from reservation r
         join food f on f.id = r.food_id
group by f.id
ORDER BY moneyMade DESC;

--most gluttonous user--
select u.username, sum(quantity) totalfoodeaten
from users u
         join reservation r on u.id = r.user_id
group by u.username
order by totalfoodeaten desc;