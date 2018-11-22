package server.OpenTriviaDataBaseAPIHook;

/**
 *
 * @author nikalsh
 */
class HookConfig {

    private String amount = "10";
    private String category = ApiConstants.CATEGORY_ANY;
    private String difficulty = ApiConstants.DIFFICULTY_ANY;
    private String type = ApiConstants.TYPE_MULTI;
    private String encoding = ApiConstants.ENCODING_RFC_3986;
    private String token = "";

    private String QuestionRequest = String.format("https://opentdb.com/api.php?amount=%s%s%s%s%s",
            amount, category, difficulty, type, encoding, token);

    public HookConfig(String token) {
        setToken(token);
    }

    private void setToken(String token) {
        this.token = "&token=" + token;

    }

    public void updateToken(String token) {
        setToken(token);
    }

    public String getQuestionRequest() {
        return QuestionRequest;
    }

    public int getAmount() {
        return Integer.parseInt(amount);
    }

}
