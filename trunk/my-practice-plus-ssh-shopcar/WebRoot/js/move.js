var currentMoveObj = null;    //��ǰ�϶�����
var relLeft;    //��갴��λ����Զ���λ��
var relTop;
function f_mdown(obj)
{
    currentMoveObj = obj;        //�����󱻰���ʱ����¼�ö���
    currentMoveObj.style.position = "absolute";    
    relLeft = event.x - currentMoveObj.style.pixelLeft;
    relTop = event.y - currentMoveObj.style.pixelTop;
}
window.document.onmouseup = function()
{
    currentMoveObj = null;    //������ͷ�ʱͬʱ�ͷ��϶�����
}
function f_move(obj)
{
    if(currentMoveObj != null)
    {
        currentMoveObj.style.pixelLeft=event.x-relLeft;
        currentMoveObj.style.pixelTop=event.y-relTop;
    }
}