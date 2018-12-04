import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*
 * @author F3ver1
 * @date 2018/12/4 18:29
 */

/**
 * @Author F3ver1
 * @Date 2018/12/2 0002 上午 7:52
 */

/**
 * 1.找到武将中武力前三的hero，用流的排序
 * 2.按出生地分组
 * 3.找出寿命前三的武将
 * 4.女性寿命中最高的
 * 5.找出武力排名前三
 * 6.按各个年龄段分组
 * 7.按武力段分组
 * 8.按出生地分组后，统计各组人数
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        //找出武力最大三个武将
        Stream<String> lines = Files.lines(Paths.get("heroes.txt"));
        threeMaxPowerHero(lines);
        System.out.println();
        System.out.println("------------------------------");
        //找出寿命最长的三个武将
        Stream<String> lines1 = Files.lines(Paths.get("heroes.txt"));

        lifeTimeThreeMaxHero(lines1);
        System.out.println();
        System.out.println("------------------------------");
        //女武将中寿命最高的
        Stream<String> lines2 = Files.lines(Paths.get("heroes.txt"));
        femaleHerolifeTimeMax(lines2);

        System.out.println();
        System.out.println("------------------------------");
        //按出生地将武将分类
        Stream<String> lines3 = Files.lines(Paths.get("heroes.txt"));
        groupingByBirth(lines3);
        System.out.println();
        System.out.println("------------------------------");
    }

    private static void femaleHerolifeTimeMax(Stream<String> lines) {
        lines.map(str -> str.split("\t"))
                .map(array -> new Hero(Integer.parseInt(array[0]),
                        array[1], array[2], array[3],
                        Integer.parseInt(array[4]),
                        Integer.parseInt(array[5]),
                        Integer.parseInt(array[6]))).filter(a -> a.getSex().equals("女")).sorted((a, b) ->
                (b.getDeath() - b.getBirth()) - (a.getDeath() - a.getBirth())).
                limit(1).forEach(a -> System.out.println("最高寿命女武将是" +
                (a.getName()) + "，寿命是" + (a.getDeath() - a.getBirth()) + "岁"));
    }

    private static void groupingByBirth(Stream<String> lines) {
        Map<String, List<Hero>> map = lines.map(str -> str.split("\t"))
                .map(array -> new Hero(Integer.parseInt(array[0]),
                        array[1], array[2], array[3],
                        Integer.parseInt(array[4]),
                        Integer.parseInt(array[5]),
                        Integer.parseInt(array[6]))).collect(Collectors.groupingBy(Hero -> {
                    return Hero.getLocal();
                }));
        Set<String> keys = map.keySet();
        for (String k : keys) {
            System.out.println("出生地：" + k);
            List<Hero> list = map.get(k);
            for (Hero s : list) {
                System.out.println("\t" + s.getName());
            }
            System.out.println();
        }
    }

    private static void threeMaxPowerHero(Stream<String> lines) {
        List<Hero> list1 = lines.map(str -> str.split("\t")).
                map(array -> new Hero(Integer.parseInt(array[0]), array[1], array[2], array[3],
                        Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]))).
                sorted((a, b) -> -(a.getPower() - b.getPower())).limit(3).collect(Collectors.toList());
        System.out.print("武力前三的武将分别是：");
        for (Hero hero : list1) {
            System.out.print("\n" + hero.getName() + ",武力值是：" + hero.getPower());
        }
    }

    private static void lifeTimeThreeMaxHero(Stream<String> lines) {
        List<Hero> list2 = lines.map(str -> str.split("\t"))
                .map(array -> new Hero(Integer.parseInt(array[0]),
                        array[1], array[2], array[3],
                        Integer.parseInt(array[4]),
                        Integer.parseInt(array[5]),
                        Integer.parseInt(array[6]))).sorted((a, b) ->
                        (b.getDeath() - b.getBirth()) - (a.getDeath() - a.getBirth())).
                        limit(3).collect(Collectors.toList());
        System.out.print("寿命前三的武将分别是：");
        for (Hero hero : list2) {
            System.out.print("\n" + hero.getName() + "，寿命值是：" + (hero.getDeath() - hero.getBirth()) + "岁");
        }
    }
}


