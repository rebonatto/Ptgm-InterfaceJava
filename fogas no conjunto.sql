SELECT c.codcaptura, c.dataatual, cap.codcaptura, cap.dataatual
FROM capturaatual as c, (select * from capturaatual) as cap
where c.dataatual <= cap.dataatual
and cap.dataatual <= ADDTIME(c.dataatual, '0 0:0:1') 
and c.codcaptura != cap.codcaptura
and c.dataatual > '2014-01-01 00:00:00'
order by c.codcaptura, cap.codcaptura
limit 10000
