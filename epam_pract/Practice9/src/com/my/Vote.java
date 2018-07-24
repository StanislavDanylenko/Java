package com.my;

import java.util.ArrayList;
import java.util.List;

public class Vote {
    int count;
    String name;
    List<String> voters = new ArrayList<>();

    public void setCount(int count) {
        this.count = count;
    }

    public void setVoters(List<String> voters) {
        this.voters = voters;
    }

    public Vote(String name) {
        this.name = name;
    }

    public void incrementCount(){
        count++;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public List<String> getVoters() {
        return voters;
    }

    private String getVotersStr(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < voters.size(); i++) {
            if (i != voters.size() - 1){
                sb.append(voters.get(i)).append(",");
            } else {
                sb.append(voters.get(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "<td style='border: 1px solid black'><p><input type=\"radio\" name=\"group1\" value=\""+ getName() +
                "\">"  + getName() + "</p></td><td style='border: 1px solid black'>" + getCount() + "</td><td style='border: 1px solid black'>" + getVotersStr() + "</td>";
    }
}
