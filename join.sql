select s.budget, s.name, s.course, u.name from students s join universities u
on s.university_id = u.id where s.budget = true;

select s.name, s.course from students s join universities u
on s.university_id = u.id where u.name = 'U4';

select s.name, u.name, s.enroll_date from students s join universities u
on s.university_id = u.id where s.enroll_date < '2018.12.31';