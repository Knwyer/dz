class VenueBookingSystem:
    def __init__(self):
        # Даты, на которые площадка уже забронирована
        self.booked_dates = ["2024-12-25", "2024-12-31"]

    def check_availability(self, requested_date):
        """
        Проверяет доступность площадки на указанную дату.
        """
        print(f"Проверка доступности на {requested_date}...")
        if requested_date in self.booked_dates:
            print("Площадка недоступна. Выберите другую дату.")
            return False
        else:
            print("Площадка доступна!")
            return True

    def process_payment(self, amount):
        """
        Симулирует обработку платежа.
        """
        print(f"Проводится оплата {amount} руб...")
        # Логика платежа, возвращает успех
        payment_success = True
        if payment_success:
            print("Платеж успешно проведен.")
            return True
        else:
            print("Платеж не прошел. Повторите попытку.")
            return False

    def book_venue(self, date, amount):
        """
        Основной процесс бронирования площадки.
        """
        if self.check_availability(date):
            confirm = input(f"Площадка доступна. Стоимость: {amount} руб. Подтвердите бронирование? (да/нет): ").strip().lower()
            if confirm == "да":
                if self.process_payment(amount):
                    self.booked_dates.append(date)
                    print("Бронирование подтверждено! Администратор уведомлен.")
                else:
                    print("Ошибка оплаты. Попробуйте снова.")
            else:
                print("Бронирование отменено.")
        else:
            print("Пожалуйста, выберите другую дату.")

# Пример использования
if __name__ == "__main__":
    system = VenueBookingSystem()
    requested_date = input("Введите дату бронирования (ГГГГ-ММ-ДД): ").strip()
    system.book_venue(requested_date, 5000)
