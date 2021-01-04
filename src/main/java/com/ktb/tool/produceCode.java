package com.ktb.tool;

/**
 * @ Description   :  生成6位随机数
 * @ Author        :  Ling_Shao
 * @ CreateDate    :  2020/4/25 19:06
 * @ UpdateUser    :  Ling_Shao
 * @ UpdateDate    :  2020/4/25 19:06
 * @ UpdateRemark  :  修改内容
 * @ Version       :  1.0
 */
public class  produceCode extends Thread{

    public String initCode(){
        int Code1 = (int) ((Math.random()*9+1)*100000);
        String Code = String.valueOf(Code1);
        return Code;
    }
}
