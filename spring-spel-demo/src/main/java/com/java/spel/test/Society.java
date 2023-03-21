package com.java.spel.test;

import java.util.*;

public class Society {

    private String name;

    public static String Advisors = "advisors";
    public static String President = "president";

    private List<Inventor> members = new ArrayList<Inventor>();

    public void setMembers(List<Inventor> members) {
        this.members = members;
    }

    private Map officers = new HashMap();

    public List getMembers() {
        return members;
    }

    public Map getOfficers() {
        return officers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMember(String name) {
        for (Inventor inventor : members) {
            if (inventor.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean allIn(List<String> list) {
        boolean allEqual = false;
        for (String name : list) {
            boolean isEquals = false;
            for (Inventor inventor : members) {
                if (inventor.getName().equals(name)) {
                    isEquals = true;
               }
            }
            if (!isEquals) {
                allEqual = false;
                break;
            } else {
                allEqual = true;
            }
        }
        return allEqual;
    }

}