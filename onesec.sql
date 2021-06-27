-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主机： localhost
-- 生成日期： 2021-05-27 16:28:19
-- 服务器版本： 5.7.17-log
-- PHP 版本： 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 数据库： `onesec`
--

-- --------------------------------------------------------

--
-- 表的结构 `article`
--

CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `category` int(11) NOT NULL DEFAULT '0',
  `publishdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `banner` varchar(200) DEFAULT 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg',
  `title` varchar(50) NOT NULL,
  `content` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `article`
--

INSERT INTO `article` (`id`, `author`, `category`, `publishdate`, `banner`, `title`, `content`) VALUES
(1, 1, 0, '2012-12-24 15:59:59', '//arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '文章题目：题目在这里', '&lt;blockquote&gt;【译文信息】\n翻译：Sylpha\n译文来源：微信公众号「膜法少女滑稽果（huajimoe）」\n参考文章：部分翻译参照「呆鸟飞两秒@贴吧」的译文（&lt;a href=&quot;http://tieba.baidu.com/p/4952793205&quot; target=&quot;_blank&quot; rel=&quot;noopener noreferrer&quot;&gt;http://tieba.baidu.com/p/4952793205&lt;/a&gt;）\n【原文信息】\n作者：Wilhelm Donko@Crunchyroll\n原文地址：&lt;a href=&quot;http://www.crunchyroll.com/anime-feature/2017/01/21-1/feature-anime-vs-real-life-miss-kobayashis-dragon-maid&quot; target=&quot;_blank&quot; rel=&quot;noopener noreferrer&quot;&gt;http://www.crunchyroll.com/anime-feature/2017/01/21-1/feature-anime-vs-real-life-miss-kobayashis-dragon-maid&lt;/a&gt;&lt;/blockquote&gt;\n&lt;!--more--&gt;\n&lt;blockquote&gt;京阿尼在背景重现真实街景的细节方面十分的良心，看了本文你就感受更加深刻...&lt;/blockquote&gt;\n我知道，大家都在讨论「小林家的龙女仆」第二集中托尔和康娜之间的打斗场景有多么精彩。然而，京阿尼不仅给我们展现了可能是一月番中最好的战斗场景，而且还有许多优秀的背景画面。至今， 京都动画一直给我们带来一流的视觉体验，继上一季「吹响吧！上低音号」的美丽写实风格之后，「小林家的龙女仆」拥有更加色彩艳丽的观感。不用说，背景就很棒，而其独特的风格实际上使动画中的位置与现实中的地理位置相比更加interesting了。\n\n这促使我开始回顾动画的背景设置。啤酒猛灌了一夜后，小林摇摇晃晃地漫步进森林，并邀请一只负伤的龙去她的公寓居住。&lt;del&gt;躲也躲不掉。&lt;/del&gt;&lt;span style=&quot;color: #c0c0c0;&quot;&gt;（原句中的It happens to the best of us不太好翻译...）&lt;/span&gt;第二天，托尔动身前往她在埼玉县越谷市的新家（飞过了秋叶原，在那里她得出了自己女仆服的想法）。越谷市位于日本略东北部，预计人口约为337,000。现在我们开始对比照片吧！\n\n*注意：所有实景照片取自Google街景。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_1.png&quot; alt=&quot;&quot;/&gt;\n\n「小林家的龙女仆」中的「胧塚（Oborozuka）站」是基于现实中的越谷站来设计的。越谷站是东京晴空塔线路的一个车站，由东武铁路运营。照片展示的是车站的西出口。京阿尼总是尽可能的重现现实原型，这部作品也不例外。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_2.png&quot; alt=&quot;&quot;/&gt;\n\n除了越谷市，这部作品的另外一个主要位置是小林的工作场所。她的公司位于东京市中心的中央区，相对靠近东日本桥站。虽然路口对应的很准确，但她的办公楼实际上是不存在的。现实中是一个名为「Chicken Place（chiken pureisu）」的餐厅占据了这一位置。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_3.png&quot; alt=&quot;&quot;/&gt;\n\n我必须承认我以前不是「怪物女孩」这类作品的粉丝，但这一季动画中的「小林家的龙女仆」和「亚人酱有话要说」强烈的首映或许会改变我的想法。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_4.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_5.png&quot; alt=&quot;&quot;/&gt;\n\n虽然真正的标志给出的是墨田区与新宿区的方向，但这部动画里包含了一些有关于龙的漂亮的双关语，因为标志上都包含了龙的汉字。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_6.png&quot; alt=&quot;&quot;/&gt;\n\n小林、托尔和她的同事下班后前往的居酒屋。我很好奇是否允许龙喝酒。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_7.png&quot; alt=&quot;&quot;/&gt;\n\n小林在回家的路上睡着以后，托尔决定直接飞回越谷市。以他们所在的地方而言这将是一段很长的路，所以一个会飞的龙女仆实际上很方便应对这种情况。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_8.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_9.png&quot; alt=&quot;&quot;/&gt;\n\n这两个镜头都已经回到了越谷市。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_10.png&quot; alt=&quot;&quot;/&gt;\n\n这个接近越谷站西出口的居酒屋在动画里叫「sakesuke」，然而在现实中却叫做「don」。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_11.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_12.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_13.png&quot; alt=&quot;&quot;/&gt;\n\n第二集的商店街在现实中是不存在的，至少它不是位于它该在的地方&mdash;&mdash;越谷站的外面。除了左边的建筑物和缺失的自动售货机，右边的建筑和商店都与动画中类似。图片下半部分的两棵树和四个小路障也是一样的，所以基本可以确定这里就是商店街所在的地方。不幸我没有读过原作，所以不确定是否借鉴了原作漫画。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_14.png&quot; alt=&quot;&quot;/&gt;\n\n这里就有些展开了，但还是请让我来解释一下。托尔提到购物中心使她感到不适，而且会使她想起城堡。动画所展示的镜头看起来很可怕，很像历史上有名的十字军城堡&mdash;&mdash;叙利亚骑士堡。&lt;span style=&quot;color: #c0c0c0;&quot;&gt;（PS：这个城堡在阿勒颇市中心，政府军守了三年，2016年才解围。）&lt;/span&gt;至少可以说，这座城堡的形状、颜色和拱桥影响到了动画的镜头。但这还没完，在城堡附近还有圣乔治修道院。圣乔治最著名的传说之一是他杀了一条龙，而修道院显然也包含了这几幅画的传说。我们从动画的倒叙镜头里知道，托尔有一个关于人类的稍微有些悲壮的故事（康娜甚至以为他已经死了），而小林也在山上发现了因为身上插了根剑而受伤的托尔（而圣乔治用的是一根长矛）。无论如何，我可能只是做了过多的解读，请保持怀疑，因为这些全都是猜测。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_15.png&quot; alt=&quot;&quot;/&gt;\n\n所以托尔实际上外出前把尾巴切断了？\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_16.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_17.png&quot; alt=&quot;&quot;/&gt;\n\n托尔似乎不介意她的角。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_18.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_19.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_20.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_21.png&quot; alt=&quot;&quot;/&gt;\n\n托尔像康娜解释人类世界是如何工作的那一段可能是我最喜欢的部分了。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_22.png&quot; alt=&quot;&quot;/&gt;\n\n上方和下方的镜头都在越谷站西出口的前面。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_23.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_24.png&quot; alt=&quot;&quot;/&gt;\n\n这个小广场也靠近越谷站。不巧的是，我并没有发现托尔和康娜互相弹射升空的那个跷跷板。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_25.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_26.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_27.png&quot; alt=&quot;&quot;/&gt;\n\n在第二集我们能看到越谷站东侧。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_28.png&quot; alt=&quot;&quot;/&gt;\n\n像前面所说的，车站的名称在动画中被改为「胧塚站」。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_29.png&quot; alt=&quot;&quot;/&gt;\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_30.png&quot; alt=&quot;&quot;/&gt;\n\n越谷双城购物中心在越谷站的外面。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_31.png&quot; alt=&quot;&quot;/&gt;\n\n这让我怀疑是否真的有龙在我们身边，使用魔法来阻挡我们对它们的感知。然而，当我想到托尔强大到足以带来末日，那我觉得就算它们不存在我也能接受。\n\n&lt;img src=&quot;https://blog-1253302621.cos.ap-shanghai.myqcloud.com/blog/img/20180806_maidragon_32.png&quot; alt=&quot;&quot;/&gt;\n\n「小林家的龙女仆」与「亚人酱有话要说」，这一季的「怪物女孩」题材类动画，你更喜欢哪一部？此外，你怎么看待作品里与真实世界相似的地点？欢迎在下方评论区踊跃评论！\n\n----------\n\n翻译后记：\n\n出于强迫症的心理，终于在拖拖拉拉老长时间后把这篇文章移完了。当时还是妹抖龙在好评放映，而我还在住8人间上铺的时候，看到了这篇文章，觉得自己各个方面没做出点成果，略心有不满；然而自己也就英语好点，就在晚上花了四个小时在床上抱着笔记本翻译完了，放在了自己的微信公众号上（然而那微信公众号万年也更不了一次，现在已经相当于鸽了，仍在关注的27个听众，感谢你们的不离不弃...），按下发布的一瞬间，整个腿已经麻的动不了了...迁移到博客的过程中修正了部分标点符号和翻译错误。\n\nTimeline：\n\n20170723 23:30 成文并发布于微信公众号\n20180606 02:16 移动到博客并修正了部分标点符号和翻译错误'),
(2, 1, 0, '2013-12-24 15:59:59', NULL, 'api测试文章', '啊啊啊啊api测试'),
(3, 1, 0, '2014-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章3', '测试文章3'),
(4, 1, 0, '2015-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章4', '测试文章3'),
(5, 1, 0, '2016-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章5', '测试文章3'),
(6, 1, 0, '2017-12-24 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(7, 1, 1, '2017-12-24 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(8, 2, 1, '2018-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章2', '测试文章2'),
(9, 1, 0, '2019-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章3', '测试文章3'),
(10, 1, 0, '2020-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章4', '测试文章3'),
(11, 1, 0, '2020-12-25 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章5', '测试文章3'),
(12, 1, 0, '2020-12-26 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(13, 1, 1, '2020-12-27 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(14, 2, 1, '2013-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章2', '测试文章2'),
(15, 1, 0, '2014-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章3', '测试文章3'),
(16, 1, 0, '2015-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章4', '测试文章3'),
(17, 1, 0, '2016-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章5', '测试文章3'),
(18, 1, 0, '2017-12-24 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(19, 1, 1, '2017-12-24 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(20, 2, 1, '2018-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章2', '测试文章2'),
(21, 1, 0, '2019-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章3', '测试文章3'),
(24, 1, 0, '2020-12-26 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '牛仁亮刘维佳郭良孝王庭栋王清宪吴保安韩和平王李福明怀英郝志远刘向东菅二拴杜顺义王抒祥等参加开工仪式参', '测试文章3'),
(25, 1, 1, '2020-12-27 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '超长标题测试：国务院办公厅印发《新时代中央国家机关及有关单位对口支援赣南等原中央苏区工作方案》', '测试文章3'),
(26, 1, 1, '2020-12-27 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '超长标题测试：国务院办公厅印发《新时代中央国家机关及有关单位对口支援赣南等原中央苏区工作方案》', '测试文章3'),
(27, 1, 0, '2014-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章3', '测试文章3'),
(28, 1, 0, '2015-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章4', '&lt;p&gt;测试文章3111&lt;/p&gt;'),
(29, 1, 0, '2016-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章5', '测试文章3'),
(30, 1, 0, '2017-12-24 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(31, 1, 1, '2017-12-24 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(32, 2, 1, '2018-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章2', '测试文章2'),
(33, 1, 0, '2019-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章3', '测试文章3'),
(34, 1, 0, '2020-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章4', '测试文章3'),
(35, 1, 0, '2020-12-25 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章5', '测试文章3'),
(36, 1, 0, '2020-12-26 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(37, 1, 1, '2020-12-27 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(38, 2, 1, '2013-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章2', '测试文章2'),
(39, 1, 0, '2014-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章3', '测试文章3'),
(40, 1, 0, '2015-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章4', '测试文章3'),
(41, 1, 0, '2016-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章5', '测试文章3'),
(42, 1, 0, '2017-12-24 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(43, 1, 1, '2017-12-24 15:59:59', 'https://img.36krcdn.com/20210503/v2_4f9e9357c926497b97a65273bf71d0eb_img_jpg', '测试文章6', '测试文章3'),
(44, 2, 1, '2018-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章2', '测试文章2'),
(45, 1, 0, '2019-12-24 15:59:59', 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '测试文章3', '测试文章3'),
(46, 1, 0, '2021-05-26 09:25:46', '//arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', 'api测试文章', '啊啊啊啊api测试'),
(47, 1, 0, '2021-05-26 09:35:35', '//arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', 'api测试文章', '啊啊啊啊api测试'),
(51, 1, 0, '2021-05-26 09:55:10', '//arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '啊啊', '&lt;p&gt;a啊&lt;/p&gt;'),
(52, 1, 9, '2021-05-26 10:01:44', '//arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '甘肃景泰山地马拉松越野赛最新调查处置工作进展公布', ' &lt;p&gt;新华社兰州5月25日电 记者从第四届黄河石林山地马拉松百公里越野赛公共安全事件第二次新闻发布会获悉，目前一半以上遇难人员家属已基本接受补偿协议，受伤8人中除1人伤情较重仍在治疗，其余均已出院。遇难者团体意外险和自然灾害救助保险金已全部到位，联合调查组也已进驻实施调查。&lt;br/&gt;&lt;/p&gt;&lt;p&gt;&lt;br/&gt;据了解，在甘肃省白银市景泰县举办的第四届黄河石林山地马拉松百公里越野赛中，172人参赛，其中21人遇难、8人受伤。&lt;br/&gt;&lt;br/&gt;白银市委副书记、市长张旭晨介绍，目前，21名遇难人员的161名亲属抵达白银市，一半以上遇难人员家属已基本接受补偿协议。8名受伤参赛人员中除1人伤情较重仍在治疗，其余均已出院。&lt;br/&gt;&lt;br/&gt;张旭晨介绍，目前遇难者团体意外险和自然灾害救助保险金已全部准备到位。同时，赛事运营方甘肃晟景体育文化发展有限公司的补偿资金和政府救助资金也已到位。&lt;br/&gt;&lt;br/&gt;甘肃省应急管理厅厅长、联合调查组组长黄泽元介绍，24日甘肃省委、省政府成立联合调查组，并邀请国家体育总局、国家气象局的马拉松赛事专家和气象专家参与调查。目前，联合调查组已正式进驻白银市及景泰县实施调查。&lt;br/&gt;&lt;br/&gt;黄泽元介绍，此次联合调查将准确认定事件性质，查明主办方、承办方、运营方及相关部门、单位和人员责任。（记者郭刚、李杰、范培珅）&lt;/p&gt;');

-- --------------------------------------------------------

--
-- 表的结构 `category`
--

CREATE TABLE `category` (
  `catid` int(11) NOT NULL,
  `catname` varchar(20) NOT NULL,
  `catindex` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `category`
--

INSERT INTO `category` (`catid`, `catname`, `catindex`) VALUES
(0, '基本分类', 0),
(1, '测试分类', 0),
(2, '科技', 98),
(4, '时政', 97),
(6, '国际', 96),
(9, '文化', 97),
(13, '军事', 85),
(15, '体育', 0);

-- --------------------------------------------------------

--
-- 表的结构 `comment`
--

CREATE TABLE `comment` (
  `cid` int(11) NOT NULL,
  `article` int(11) NOT NULL,
  `author` int(11) NOT NULL,
  `comment` longtext NOT NULL,
  `publishdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `comment`
--

INSERT INTO `comment` (`cid`, `article`, `author`, `comment`, `publishdate`) VALUES
(1, 1, 2, '评论测试', '2021-05-14 03:22:33'),
(3, 1, 1, '评论测试3', '2021-05-14 03:22:33'),
(4, 1, 1, '评论测试', '2021-05-14 03:22:33'),
(5, 1, 1, '评论测试2', '2021-05-14 03:22:33'),
(6, 1, 1, '评论测试3', '2021-05-14 03:22:33'),
(7, 1, 1, '评论测试', '2021-05-14 03:22:33'),
(8, 1, 1, '评论测试2', '2021-05-14 03:22:33'),
(9, 1, 1, '评论测试3', '2021-05-14 03:22:33'),
(10, 1, 1, '评论测试', '2021-05-14 03:22:33'),
(11, 1, 1, '评论测试2', '2021-05-14 03:22:33'),
(13, 1, 1, '评论测试', '2021-05-14 03:22:33'),
(14, 1, 1, '评论测试2', '2021-05-14 03:22:33'),
(15, 1, 1, '评论测试3', '2021-05-14 03:22:33'),
(16, 1, 1, '评论测试', '2021-05-14 03:22:33'),
(17, 1, 1, '评论测试2', '2021-05-14 03:22:33'),
(18, 1, 1, '评论测试3', '2021-05-14 03:22:33'),
(22, 1, 3, '评论测试', '2021-05-14 03:22:33'),
(24, 25, 1, '不正经的科普\r\n\r\n1本篇故事是漫画里最长的一篇，意思是一章八十多页，肯定有必第一篇更长的但是会被分开。作者非常明智，第一个故事非常令人感动而且收获大量观众\r\n\r\n2很多细节都在一些事情中交代，比如少年说照顾婆婆们，但是婆婆们都死了，也展示了坟墓是什么样子的，所以后来少年看到崩塌的车队和坟墓，瞬间就绝望了。\r\n\r\n3其实可用的木头不多了，这一点也鼓动了少年远行，少年孤独太久了，以至于希望与绝望落差太大。\r\n\r\n4不灭其实的确说话了，但是少年自言自语五年，以为是幻觉。\r\n\r\n5不灭最后把少年扶上了椅子，并且记住了两件事情\r\n\r\n向少年走过的方向走，水果\r\n\r\n甚至满脑子都是水果\r\n\r\n6不灭什么都要学，刚开始狼顺拐是有原因的，因为不会，还有变成少年的时候，姿势诡异也是因为这个', '2021-05-14 03:22:33'),
(25, 1, 1, '啊啊啊啊啊', '2021-05-26 07:30:08'),
(26, 1, 1, 'api测试', '2021-05-26 07:40:39'),
(27, 2, 1, 'api测试2', '2021-05-26 07:43:29'),
(28, 45, 1, '啊啊啊', '2021-05-26 07:53:54'),
(29, 1, 1, '啊啊啊啊哈哈哈', '2021-05-26 07:54:32'),
(30, 1, 1, '啊啊啊啊哈哈哈', '2021-05-26 07:54:54'),
(31, 1, 1, '啊啊啊啊哈哈哈', '2021-05-26 07:55:07'),
(34, 1, 1, '啊啊啊啊哈哈哈', '2021-05-26 07:57:59'),
(35, 1, 1, '啊啊啊', '2021-05-26 07:59:58'),
(37, 1, 1, '啊啊啊1111', '2021-05-26 08:01:31'),
(40, 1, 1, '蛤蛤蛤', '2021-05-26 08:02:43'),
(41, 28, 1, '大家好\n我是杨颜xf', '2021-05-26 08:07:29'),
(43, 35, 1, '\\\\&#39;&#39;&#39;', '2021-05-26 08:32:11'),
(44, 45, 2, '啊啊啊啊', '2021-05-26 09:18:26'),
(45, 53, 1, 'aaaa', '2021-05-26 13:38:03');

-- --------------------------------------------------------

--
-- 表的结构 `trending`
--

CREATE TABLE `trending` (
  `id` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `banner` varchar(200) DEFAULT NULL,
  `link` varchar(200) NOT NULL,
  `itemindex` int(11) NOT NULL,
  `lastchange` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `trending`
--

INSERT INTO `trending` (`id`, `title`, `banner`, `link`, `itemindex`, `lastchange`) VALUES
(1, '用“小红书式”打法进攻美团，阿里的“吃货笔记”能做成吗？', 'https://img.36krcdn.com/20210517/v2_fcbc4ddd5b3a4490b0cfc4b1518821d0_img_jpeg?x-oss-process=image/resize,m_mfit,w_600,h_400,limit_0/crop,w_600,h_400,g_center', '//36kr.com/p/1229618718494083', 99, '2021-05-18 17:21:26'),
(4, '新 iMac 首发体验：9 年了，苹果终于想开了', 'https://img.huxiucdn.com/article/cover/202105/18/180440989841.jpg', 'https://www.huxiu.com/article/428763.html', 98, '2021-05-18 16:51:30'),
(6, '无banner推荐条目测试', '', '//qq.com', 70, '2021-05-18 17:22:28'),
(7, '1花白菜价买进口食品，临期食品到底香不香', 'http://img.q.jiagle.com/media/2016/12/19/9745091482124404.jpg', '/#/news/detail/1', 100, '2021-05-27 08:04:29'),
(8, '无banner条目测试：百度', '', '//baidu.com', 11, '2021-05-18 17:22:49'),
(9, '测试6', '', '//baidu.com', 12, '2021-05-18 17:54:41'),
(10, '甘肃景泰山地马拉松越野赛最新调查处置工作进展公布', '//arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '/#/news/detail/52', 100, '2021-05-27 08:11:40'),
(11, '啊啊', '//arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/newsimg.jpg', '/#/news/detail/51', 100, '2021-05-27 08:12:02');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `uid` int(11) NOT NULL,
  `usertype` int(11) NOT NULL DEFAULT '0' COMMENT '1为管理员，0为普通读者,-1为已注销',
  `username` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL COMMENT '密码长度8-20，加密后长度<50',
  `email` varchar(50) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `birthday` timestamp NOT NULL DEFAULT '1999-12-31 16:00:00',
  `sex` int(11) NOT NULL DEFAULT '0' COMMENT '0保密，1男，2女',
  `avatar` varchar(200) NOT NULL DEFAULT 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/akalin.jpg',
  `regtime` timestamp NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`uid`, `usertype`, `username`, `password`, `email`, `nickname`, `birthday`, `sex`, `avatar`, `regtime`) VALUES
(1, 1, 'adminadmin', 'dd94709528bb1c83d08f3088d4043f4742891f4f', 'admin@ytwenjing.cn', '管理员小助手1', '1999-12-31 16:00:00', 1, 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/akalin.jpg', '2021-04-21 15:59:59'),
(2, -1, 'testuser', '0', 'email@email.com', '[已注销]', '2001-01-02 00:00:00', 1, '', '2019-12-31 16:00:00'),
(3, -1, 'zhangsan1123', '0', 'zhangsan1123@163.com', '[已注销]', '1998-08-31 16:00:00', 2, '', '1999-12-31 15:59:59'),
(4, -1, 'testuser1', '0', 'testuse1r@testuser.cn', '[已注销]', '1999-12-31 16:00:00', 0, '', '1999-12-31 15:59:59'),
(5, 0, 'zhangsan', 'dd94709528bb1c83d08f3088d4043f4742891f4f', 'tst!@tst.cn', 'iam1ssss', '1992-07-09 00:00:00', 1, 'https://arraycats-1253302621.cos.ap-shanghai.myqcloud.com/img/akalin.jpg', '2021-04-21 20:55:13'),
(6, -1, 'sylpha111', '0', 'zhaolusylpha1@gmail.com', '[已注销]', '1999-01-25 16:00:00', 0, '', '2021-04-21 21:27:31');

--
-- 转储表的索引
--

--
-- 表的索引 `article`
--
ALTER TABLE `article`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`catid`);

--
-- 表的索引 `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`cid`);

--
-- 表的索引 `trending`
--
ALTER TABLE `trending`
  ADD PRIMARY KEY (`id`);

--
-- 表的索引 `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`uid`),
  ADD UNIQUE KEY `uid` (`uid`),
  ADD UNIQUE KEY `username` (`username`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `article`
--
ALTER TABLE `article`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- 使用表AUTO_INCREMENT `category`
--
ALTER TABLE `category`
  MODIFY `catid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- 使用表AUTO_INCREMENT `comment`
--
ALTER TABLE `comment`
  MODIFY `cid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- 使用表AUTO_INCREMENT `trending`
--
ALTER TABLE `trending`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `uid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
