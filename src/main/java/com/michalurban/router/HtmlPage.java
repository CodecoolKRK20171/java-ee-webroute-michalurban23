package com.michalurban.router;

class HtmlPage {

    String getFullResponse(String response) {

        return getTop() + response + getBottom();
    }

    private String getTop() {

        return "<!DOCTYPE html>" +
                "\n<html xml:lang=\"pl\" lang=\"pl\">" +
                "\n<head>" +
                "\n   <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /> " +
                "\n   <Title>Testing Annotations</Title>" +
                "\n</head>" +
                "\n<body>\n";
    }

    private String getBottom() {

        return "\n</body>\n" +
                "\n</html>";
    }

    String getMainPage() {

        return "<h1>Hello</h1>" +
                "<a href=\"\\form\"><button>Go to form</button></a>";
    }

    String getForm() {

        return "<form method=\"POST\">" +
                "<label for=\"message\">Type anything</label>" +
                "<input type=\"text\" id=\"message\" name=\"message\" required />" +
                "<input type=\"submit\" value=\"SEND!\" />";
    }

    String getResult(String message) {

        return "<h2>Your reversed input:</h2>" +
                "<h4 style=\"color: green;\">" + message + "</h4>" +
                "<a href=\"\\form\"><button>Go back</button></a>" +
                "<a href=\"\\\"><button>Go to start</button></a>";
    }
}
