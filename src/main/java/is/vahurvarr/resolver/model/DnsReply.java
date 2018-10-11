package is.vahurvarr.resolver.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DnsReply {

    @SerializedName("Status")
    private int status;

    @SerializedName("TC")
    private boolean tc;

    @SerializedName("RD")
    private boolean rd;

    @SerializedName("RA")
    private boolean ra;

    @SerializedName("AD")
    private boolean ad;

    @SerializedName("CD")
    private boolean cd;

    @SerializedName("Question")
    private List<Question> question;

    @SerializedName("Answer")
    private List<AuthorityAnswer> answer;

    @SerializedName("Authority")
    private List<AuthorityAnswer> authority;

    private String comment;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isTc() {
        return tc;
    }

    public void setTc(boolean tc) {
        this.tc = tc;
    }

    public boolean isRd() {
        return rd;
    }

    public void setRd(boolean rd) {
        this.rd = rd;
    }

    public boolean isRa() {
        return ra;
    }

    public void setRa(boolean ra) {
        this.ra = ra;
    }

    public boolean isAd() {
        return ad;
    }

    public void setAd(boolean ad) {
        this.ad = ad;
    }

    public boolean isCd() {
        return cd;
    }

    public void setCd(boolean cd) {
        this.cd = cd;
    }

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    public List<AuthorityAnswer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<AuthorityAnswer> answer) {
        this.answer = answer;
    }

    public List<AuthorityAnswer> getAuthority() {
        return authority;
    }

    public void setAuthority(List<AuthorityAnswer> authority) {
        this.authority = authority;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "DnsReply{" +
                "status=" + status +
                ", tc=" + tc +
                ", rd=" + rd +
                ", ra=" + ra +
                ", ad=" + ad +
                ", cd=" + cd +
                ", question=" + question +
                ", answer=" + answer +
                ", authority=" + authority +
                ", comment='" + comment + '\'' +
                '}';
    }

}
