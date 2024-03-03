```roomsql
select university,
    count(question_id) / count(distinct qpd.device_id) as avg_answer_cnt
from question_practice_detail as qpd
inner join user_profile as up
on qpd.device_id=up.device_id
group by university
```
