package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupportReasonMessageGenerator {

    public SupportReasonMessageGenerator.ReasonMessage generateReasonMessage(String pqletaId) {
        SupportReasonMessageGenerator.ReasonMessage result = new SupportReasonMessageGenerator.ReasonMessage();
        String header = null;
        String filesystem = extractFilesystemSuffix(pqletaId);
        header = filesystem;
        if (filesystem == null) {
            String afterIP = extractAfterIP(pqletaId);
            header = afterIP;
        }
        if (header != null) {
            result.alarmReasonMessage = header + " load > 80%";
            result.warningReasonMessage = header + " load > 90%";
        } else {
            header = extractAfterStatus(pqletaId);
            result.alarmReasonMessage = header + " is WARNING";
            result.warningReasonMessage = header + " is ALARM";
        }
        return result;
    }

    private String extractFilesystemSuffix(String input) {
        String keyword = "filesystem";
        int index = input.indexOf(keyword);

        if (index == -1) {
            return null; // Нет слова "filesystem"
        }

        // Найдём начало нужной части — сразу после слова "filesystem"
        int startIndex = index + keyword.length();

        // Пропустим пробелы после "filesystem"
        while (startIndex < input.length() && input.charAt(startIndex) == ' ') {
            startIndex++;
        }

        if (startIndex >= input.length()) {
            return null; // Нет ничего после "filesystem"
        }

        return input.substring(startIndex);
    }

    private String extractAfterIP(String input) {
        // Регулярка для поиска IP (4 группы чисел от 0 до 255, упрощённая для задачи)
        String ipPattern = "(\\d{1,3}(?:\\.\\d{1,3}){3})";
        Pattern pattern = Pattern.compile(ipPattern);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            // Индекс конца IP
            int endOfIP = matcher.end();

            // Пропустим пробелы после IP
            int startIndex = endOfIP;
            while (startIndex < input.length() && input.charAt(startIndex) == ' ') {
                startIndex++;
            }

            if (startIndex >= input.length()) {
                return null; // После IP ничего нет
            }

            return input.substring(startIndex);
        }

        return null; // IP не найден
    }

    private String extractAfterStatus(String input) {
        String keyword = "Status";
        int index = input.indexOf(keyword);
        if (index != -1) {
            // Индекс после слова Status
            int startIndex = index + keyword.length();

            // Пропускаем пробелы после Status
            while (startIndex < input.length() && input.charAt(startIndex) == ' ') {
                startIndex++;
            }

            if (startIndex >= input.length()) {
                return null; // После Status ничего нет
            }

            return input.substring(startIndex);
        }
        return null; // Status не найден
    }

    public class ReasonMessage {
        public String warningReasonMessage = null;
        public String alarmReasonMessage = null;
    }
}
