function Trim(str){
	return $.trim(str);
}

/*
IsFloat(string,string,int or string)测试字符串,+ or - or empty,empty or 0)
功能：判断是否为浮点数、正浮点数、负浮点数、正浮点数+0、负浮点数+0
*/
function IsFloat(objStr,sign,zero)
{
    var reg;
    var bolzero;

    if(Trim(objStr)=="")
    {
        return false;
    }
    else
    {
        objStr=objStr.toString();
    }

    if((sign==null)||(Trim(sign)==""))
    {
        sign="+-";
    }

    if((zero==null)||(Trim(zero)==""))
    {
        bolzero=false;
    }
    else
    {
        zero=zero.toString();
        if(zero=="0")
        {
            bolzero=true;
        }
        else
        {
            alert("检查是否包含0参数，只可为(空、0)");
        }
    }

    switch(sign)
    {
        case "+-":
            //浮点数
            reg=/^((-?|\+?)\d+)(\.\d+)?$/;
            break;
        case "+":
            if(!bolzero)
            {
                //正浮点数
                reg=/^\+?(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            }
            else
            {
                //正浮点数+0
                reg=/^\+?\d+(\.\d+)?$/;
            }
            break;
        case "-":
            if(!bolzero)
            {
                //负浮点数
               reg=/^-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            }
            else
            {
                //负浮点数+0
                reg=/^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
            }
            break;
        default:
            alert("检查符号参数，只可为(空、+、-)");
            return false;
            break;
    }

    var r=objStr.match(reg);
    if(r==null)
    {
        return false;
    }
    else
    {
        return true;
    }
}