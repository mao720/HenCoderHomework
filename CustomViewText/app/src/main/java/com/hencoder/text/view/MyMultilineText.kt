package com.hencoder.text.view

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hencoder.text.R
import com.hencoder.text.dp

/**
 * Description:
 *
 * 2020/12/10 (https://github.com/mao720)
 */
class MyMultilineText(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val options = BitmapFactory.Options().also {
        it.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, it)
        it.inJustDecodeBounds = false
        it.inDensity = it.outWidth
        it.inTargetDensity = 150.dp.toInt()
    }
    private val bitmap =
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 24.dp
    }
//    private val text =
//        "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    private val text = "豫章故郡，洪都新府。星分翼轸，地接衡庐。襟三江而带五湖，控蛮荆而引瓯越。物华天宝，龙光射牛斗之墟；人杰地灵，徐孺下陈蕃之榻。雄州雾列，俊采星驰。台隍枕夷夏之交，宾主尽东南之美。都督阎公之雅望，棨戟遥临；宇文新州之懿范，襜帷暂驻。十旬休假，胜友如云；千里逢迎，高朋满座。腾蛟起凤，孟学士之词宗；紫电青霜，王将军之武库。家君作宰，路出名区；童子何知，躬逢胜饯。\n" +
            "\n" +
            "时维九月，序属三秋。潦水尽而寒潭清，烟光凝而暮山紫。俨骖騑于上路，访风景于崇阿；临帝子之长洲，得天人之旧馆。层峦耸翠，上出重霄；飞阁流丹，下临无地。鹤汀凫渚，穷岛屿之萦回；桂殿兰宫，即冈峦之体势。\n" +
            "\n" +
            "披绣闼，俯雕甍，山原旷其盈视，川泽纡其骇瞩。闾阎扑地，钟鸣鼎食之家；舸舰弥津，青雀黄龙之舳。云销雨霁，彩彻区明。落霞与孤鹜齐飞，秋水共长天一色。渔舟唱晚，响穷彭蠡之滨；雁阵惊寒，声断衡阳之浦。\n" +
            "\n" +
            "遥襟甫畅，逸兴遄飞。爽籁发而清风生，纤歌凝而白云遏。睢园绿竹，气凌彭泽之樽；邺水朱华，光照临川之笔。四美具，二难并。穷睇眄于中天，极娱游于暇日。天高地迥，觉宇宙之无穷；兴尽悲来，识盈虚之有数。望长安于日下，目吴会于云间。地势极而南溟深，天柱高而北辰远。关山难越，谁悲失路之人？萍水相逢，尽是他乡之客。怀帝阍而不见，奉宣室以何年？\n" +
            "\n" +
            "嗟乎！时运不齐，命途多舛。冯唐易老，李广难封。屈贾谊于长沙，非无圣主；窜梁鸿于海曲，岂乏明时？所赖君子见机，达人知命。老当益壮，宁移白首之心？穷且益坚，不坠青云之志。酌贪泉而觉爽，处涸辙以犹欢。北海虽赊，扶摇可接；东隅已逝，桑榆非晚。孟尝高洁，空余报国之情；阮籍猖狂，岂效穷途之哭！\n" +
            "\n" +
            "勃，三尺微命，一介书生。无路请缨，等终军之弱冠；有怀投笔，慕宗悫之长风。舍簪笏于百龄，奉晨昏于万里。非谢家之宝树，接孟氏之芳邻。他日趋庭，叨陪鲤对；今兹捧袂，喜托龙门。杨意不逢，抚凌云而自惜；钟期既遇，奏流水以何惭？\n" +
            "\n" +
            "呜乎！胜地不常，盛筵难再；兰亭已矣，梓泽丘墟。临别赠言，幸承恩于伟饯；登高作赋，是所望于群公。敢竭鄙怀，恭疏短引；一言均赋，四韵俱成。请洒潘江，各倾陆海云尔：\n" +
            "\n" +
            "滕王高阁临江渚，佩玉鸣鸾罢歌舞。\n" +
            "\n" +
            "画栋朝飞南浦云，珠帘暮卷西山雨。\n" +
            "\n" +
            "闲云潭影日悠悠，物换星移几度秋。\n" +
            "\n" +
            "阁中帝子今何在？槛外长江空自流。"

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(bitmap, width - (150.dp), 70.dp, paint)
        var measureWidth = floatArrayOf(0f)
        var start = 0
        var count: Int
        var lineOffset = -paint.fontMetrics.top
        while (start < text.length) {
            var offset: Float =
                if (lineOffset + paint.fontMetrics.bottom > 70.dp && lineOffset + paint.fontMetrics.top < 220.dp) 150.dp else 0f
            count =
                paint.breakText(
                    text,
                    start,
                    text.length,
                    true,
                    width.toFloat() - offset,
                    measureWidth
                )
            canvas.drawText(text, start, start + count, 0f, lineOffset, paint)
            start += count
            lineOffset += paint.fontSpacing
        }
    }
}