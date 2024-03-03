```sql
select up.university,qd.difficult_level, count(qpd.question_id)/count(distinct qpd.device_id) as avg_answer_cnt
from question_practice_detail as qpd
left join user_profile as up on up.device_id = qpd.device_id
left join question_detail as qd on qd.question_id = qpd.question_id
group by up.university,qd.difficult_level
```