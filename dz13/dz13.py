class RecruitmentProcess:
    def __init__(self):
        self.database = []

    def create_job_request(self, manager, job_details):
        print(f"{manager} создал заявку на вакансию: {job_details}")
        return {"manager": manager, "details": job_details, "status": "pending"}

    def review_job_request(self, hr, request):
        print(f"{hr} проверяет заявку...")
        if self.check_requirements(request):
            print("Заявка утверждена.")
            request["status"] = "approved"
            return True
        else:
            print("Заявка отклонена. Требуется доработка.")
            return False

    def check_requirements(self, request):
        # Условие проверки требований
        return "details" in request and len(request["details"]) > 10

    def publish_vacancy(self, request):
        print("Вакансия опубликована.")
        return True

    def receive_applications(self, candidates):
        print("Кандидаты подают заявки...")
        return [{"candidate": c, "status": "pending"} for c in candidates]

    def review_applications(self, hr, applications):
        print(f"{hr} проверяет анкеты...")
        suitable_candidates = []
        for app in applications:
            if self.check_candidate(app["candidate"]):
                print(f"Кандидат {app['candidate']} приглашен на собеседование.")
                app["status"] = "interview"
                suitable_candidates.append(app["candidate"])
            else:
                print(f"Кандидат {app['candidate']} отклонен.")
        return suitable_candidates

    def check_candidate(self, candidate):
        # Условие отбора
        return "experience" in candidate and candidate["experience"] > 3

    def conduct_interviews(self, hr, manager, candidates):
        print("Собеседования начались...")
        successful_candidates = []
        for candidate in candidates:
            if self.interview_hr(hr, candidate) and self.interview_manager(manager, candidate):
                print(f"Кандидат {candidate['name']} успешно прошел собеседование.")
                successful_candidates.append(candidate)
            else:
                print(f"Кандидат {candidate['name']} получил отказ.")
        return successful_candidates

    def interview_hr(self, hr, candidate):
        print(f"{hr} проводит интервью с {candidate['name']}.")
        return candidate["skills"] > 5

    def interview_manager(self, manager, candidate):
        print(f"{manager} проводит техническое собеседование с {candidate['name']}.")
        return candidate["technical_skills"] > 7

    def offer_position(self, candidate):
        print(f"Оффер отправлен кандидату {candidate['name']}.")
        candidate["offer_status"] = "offered"
        return True

    def confirm_offer(self, candidate):
        print(f"Кандидат {candidate['name']} принял оффер.")
        self.database.append(candidate)
        print(f"{candidate['name']} добавлен в базу данных компании.")
        return True

# Пример использования
process = RecruitmentProcess()

# Этапы
job_request = process.create_job_request("Руководитель", "Разработчик Python")
if process.review_job_request("HR-менеджер", job_request):
    process.publish_vacancy(job_request)
    applications = process.receive_applications([
        {"name": "Иван", "experience": 4, "skills": 6, "technical_skills": 8},
        {"name": "Ольга", "experience": 2, "skills": 5, "technical_skills": 6}
    ])
    suitable_candidates = process.review_applications("HR-менеджер", applications)
    successful_candidates = process.conduct_interviews("HR-менеджер", "Руководитель", suitable_candidates)
    for candidate in successful_candidates:
        if process.offer_position(candidate):
            process.confirm_offer(candidate)
