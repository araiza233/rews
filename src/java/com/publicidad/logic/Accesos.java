package com.publicidad.logic;

import java.util.List;

public interface Accesos {
    public boolean getAccesos(String user, String passwd);
    public List getMenuOptions(String user);
    public boolean modifyPassword(String user, String newPasswd);
}
