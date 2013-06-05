var currentMoveObj = null;    //当前拖动对象
var relLeft;    //鼠标按下位置相对对象位置
var relTop;
function f_mdown(obj)
{
    currentMoveObj = obj;        //当对象被按下时，记录该对象
    currentMoveObj.style.position = "absolute";    
    relLeft = event.x - currentMoveObj.style.pixelLeft;
    relTop = event.y - currentMoveObj.style.pixelTop;
}
window.document.onmouseup = function()
{
    currentMoveObj = null;    //当鼠标释放时同时释放拖动对象
}
function f_move(obj)
{
    if(currentMoveObj != null)
    {
        currentMoveObj.style.pixelLeft=event.x-relLeft;
        currentMoveObj.style.pixelTop=event.y-relTop;
    }
}