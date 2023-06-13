package evsey.novikov.web_browser;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HelloController {
    public WebView webview;
    public HTMLEditor htmlCodeEditor;
    public TextField urlTextField;
    public TextArea RichHtmlCodeEditor;

    public void onOpen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Открыть html документ");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML Files", "*.html"));
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder htmlContent = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n");
                }

                String html = htmlContent.toString();

                htmlCodeEditor.setHtmlText(html);
                webview.getEngine().loadContent(html);
                RichHtmlCodeEditor.setText(html);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void onSave(ActionEvent actionEvent) {
        String html = webview.getEngine().executeScript("document.documentElement.outerHTML").toString();

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Сохранение htlml документа");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTML Files", "*.html"));

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            saveToFile(html, file);
        }
    }

    public void onChangeEditor(ActionEvent actionEvent) {
        String htmlDoom = htmlCodeEditor.getHtmlText();
        RichHtmlCodeEditor.setText(htmlDoom);
        webview.getEngine().loadContent(htmlDoom);
    }

    public void onChangeRichEditor(ActionEvent actionEvent) {
        String htmlDoom = RichHtmlCodeEditor.getText();
        htmlCodeEditor.setHtmlText(htmlDoom);
        webview.getEngine().loadContent(htmlDoom);

    }

    private void saveToFile(String content, File file) {
        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void onUrlLoad(ActionEvent actionEvent) {
        String url = urlTextField.getText();

        if (url != null && !url.isEmpty()) {
            try {
                URL pageUrl = new URL(url);

                BufferedReader reader = new BufferedReader(new InputStreamReader(pageUrl.openStream()));
                StringBuilder htmlContent = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n\r");
                }
                reader.close();

                String html = htmlContent.toString();
                htmlCodeEditor.setHtmlText(html);
                webview.getEngine().loadContent(html);
                RichHtmlCodeEditor.setText(html);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}