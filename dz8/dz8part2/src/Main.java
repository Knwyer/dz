public class Main {

    // Абстрактный класс ReportGenerator, который содержит общий алгоритм создания отчетов
    abstract static class ReportGenerator {

        // Шаблонный метод, который задает общую последовательность шагов
        public final void generateReport() {
            collectData();
            formatData();
            createReport();
            if (customerWantsSave()) {
                saveReport();
            }
            if (customerWantsEmail()) {
                emailReport();
            }
        }

        // Шаги, которые будут переопределены в подклассах
        protected abstract void collectData();
        protected abstract void formatData();
        protected abstract void createReport();

        // Опциональные шаги (перехватываемые методы)
        protected boolean customerWantsSave() {
            return true;  // по умолчанию сохраняем отчет
        }

        protected boolean customerWantsEmail() {
            return false;  // по умолчанию не отправляем отчет по электронной почте
        }

        // Метод для сохранения отчета (общий для всех типов отчетов)
        private void saveReport() {
            System.out.println("Отчет сохранен.");
        }

        // Метод для отправки отчета по электронной почте (общий для всех типов отчетов)
        private void emailReport() {
            System.out.println("Отчет отправлен по электронной почте.");
        }
    }

    // Конкретный класс для PDF-отчета
    static class PdfReport extends ReportGenerator {

        @Override
        protected void collectData() {
            System.out.println("Сбор данных для PDF-отчета.");
        }

        @Override
        protected void formatData() {
            System.out.println("Форматирование данных для PDF-отчета.");
        }

        @Override
        protected void createReport() {
            System.out.println("Создание PDF-отчета.");
        }
    }

    // Конкретный класс для Excel-отчета
    static class ExcelReport extends ReportGenerator {

        @Override
        protected void collectData() {
            System.out.println("Сбор данных для Excel-отчета.");
        }

        @Override
        protected void formatData() {
            System.out.println("Форматирование данных для Excel-отчета.");
        }

        @Override
        protected void createReport() {
            System.out.println("Создание Excel-отчета.");
        }

        @Override
        protected boolean customerWantsSave() {
            // Можно добавить проверку пользовательского ввода для решения о сохранении
            return true;
        }
    }

    // Конкретный класс для HTML-отчета
    static class HtmlReport extends ReportGenerator {

        @Override
        protected void collectData() {
            System.out.println("Сбор данных для HTML-отчета.");
        }

        @Override
        protected void formatData() {
            System.out.println("Форматирование данных для HTML-отчета.");
        }

        @Override
        protected void createReport() {
            System.out.println("Создание HTML-отчета.");
        }

        @Override
        protected boolean customerWantsEmail() {
            // Например, HTML-отчеты по умолчанию отправляются по электронной почте
            return true;
        }
    }

    // Добавление нового типа отчета (CSV)
    static class CsvReport extends ReportGenerator {

        @Override
        protected void collectData() {
            System.out.println("Сбор данных для CSV-отчета.");
        }

        @Override
        protected void formatData() {
            System.out.println("Форматирование данных для CSV-отчета.");
        }

        @Override
        protected void createReport() {
            System.out.println("Создание CSV-отчета.");
        }

        @Override
        protected boolean customerWantsSave() {
            // В данном примере CSV-отчет может не сохраняться
            return false;
        }

        @Override
        protected boolean customerWantsEmail() {
            // CSV-отчеты также отправляются по электронной почте
            return true;
        }
    }

    // Тестирование системы отчетов
    public static void main(String[] args) {
        // Создание и тестирование PDF-отчета
        ReportGenerator pdfReport = new PdfReport();
        System.out.println("\nГенерация PDF-отчета:");
        pdfReport.generateReport();

        // Создание и тестирование Excel-отчета
        ReportGenerator excelReport = new ExcelReport();
        System.out.println("\nГенерация Excel-отчета:");
        excelReport.generateReport();

        // Создание и тестирование HTML-отчета
        ReportGenerator htmlReport = new HtmlReport();
        System.out.println("\nГенерация HTML-отчета:");
        htmlReport.generateReport();

        // Создание и тестирование CSV-отчета
        ReportGenerator csvReport = new CsvReport();
        System.out.println("\nГенерация CSV-отчета:");
        csvReport.generateReport();
    }
}
