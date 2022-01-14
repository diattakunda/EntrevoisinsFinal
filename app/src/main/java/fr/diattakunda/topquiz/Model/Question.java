package fr.diattakunda.topquiz.Model;

import java.util.List;

public class Question {
    private  String mQuestion;
    private  List<String> mChoiceList;
    private  int mAnswerIndex;

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        mAnswerIndex = answerIndex;
    }

    public Question(String question, List<String> choiceList, int answerIndex){
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;



    }
}

