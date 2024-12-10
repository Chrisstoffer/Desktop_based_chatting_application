import java.util.HashMap;

public class IdPassword {

HashMap<String,String> idpassinfo = new HashMap<String,String>();

IdPassword()
{

    
    idpassinfo.put("Aniket_anik", "Passowrd");
    idpassinfo.put("siraj", "abc1234");
    idpassinfo.put("Tareq_aziz", "asdflkj");
    idpassinfo.put("Asiful_islam", "asif1234");

}

protected HashMap<String, String> getIdpassinfo() {
 
    return idpassinfo;
}


}
