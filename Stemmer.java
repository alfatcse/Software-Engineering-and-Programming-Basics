package assignment5;
import java.util.Arrays;
public class Stemmer {
    static public String[] getNGrams(String keyword,int number)
    {
        int length=keyword.length();
        String Ngram= new String();
        String Ngram_count=new String();
        int range = length-(number-1);
        if(length>number)
        {
            for(int i=0;i<range;i++)
            {
                for(int j=i;j<number+i;j++)
                {
                    if(length-i>=number)
                    {
                        Ngram=Ngram+keyword.charAt(j);
                    Ngram_count=Ngram_count+keyword.charAt(j);
                    }
                }
                if(i<range-1)
                    Ngram=Ngram+",";
            }
            String[] words=Ngram.split(",");
            return words;
        }
        else if(length==number)
        {
            Ngram=keyword;
            String[] words=new String[1];
            words[0]=Ngram;
            return words;
        }
        else
        {
            int asterisks=number-length;
            String a=new String();
            for(int z=0;z<asterisks;z++)
            {
                a=a+"*";
            }
            Ngram=keyword;
            Ngram=Ngram+a;
            String[] words=new String[1];
            words[0]=Ngram;
            return words;
        }
    }
    private static String convertStringArrayToString(String[] strArr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr)
            sb.append(str).append(delimiter);
        return sb.substring(0, sb.length() - 1);
    }
   static public String[] getShared(String[] a,String[] b)
    {
        String str1 = convertStringArrayToString(a, ",");
        String str2 = convertStringArrayToString(b, ",");
        int i=str1.length();
        String[] first=new String[i];
        String shared=new String();
        int p=0,q=0;
        for(int m=0;m<i;m++)
        {
         if(str1.charAt(m)==',')
          {
              first[p]=str1.substring(q,m);
              q=m+1;
              p++;
          }
        }
        first[p]=str1.substring(q,i);
        int j=str2.length();
        String[] second=new String[j];
        int t=0,z=0;
        for(int n=0;n<j;n++)
        {
            if(str2.charAt(n)==',')
            {
                second[t]=str2.substring(z,n);
                z=n+1;
                t++;
            }
        }
        second[t]=str2.substring(z,j);
        int h=0;
        for(int y=0;y<=t;y++)
        {
            for(h=0;h<=p;h++)
            {
                if (first[h]!=null)
            {
                if(second[y].equals(first[h]))
                {
                    shared=shared+second[y];
                    if(h<p)
                        shared=shared+",";
                }
              }
            }
        }
        String[] words=shared.split(",");
        return words;
    }
   static public double getDistance(String[] a,String[] b)
    {
        String str1 = convertStringArrayToString(a, ",");
        String str2 = convertStringArrayToString(b, ",");
        int i=str1.length();
        String[] first=new String[i];
        int n_gram_count_a=0;
        int p=0,q=0;
        for(int m=0;m<i;m++)
        {
            if(str1.charAt(m)==',')
            {
                first[p]=str1.substring(q,m);
                q=m+1;
                p++;
                n_gram_count_a=n_gram_count_a+1;
            }
        }
        int j=str2.length();
        String[] second=new String[j];
        int t=0,z=0;
        int n_gram_count_b=0;
        for(int n=0;n<j;n++)
        {
            if(str2.charAt(n)==',')
            {
                second[t]=str2.substring(z,n);
                z=n+1;
                t++;
                n_gram_count_b=n_gram_count_b+1;
            }
        }
        n_gram_count_a=n_gram_count_a+1;
        n_gram_count_b=n_gram_count_b+1;
        String[] x=getShared(a,b);
        String  matched_n_gram= convertStringArrayToString(x, ",");
        int k=matched_n_gram.length();
        int n_gram_count_c=0;
        for(int n=0;n<k;n++)
        {
            if(matched_n_gram.charAt(n)==',')
            {
                n_gram_count_c=n_gram_count_c+1;
            }
        }
        n_gram_count_c=n_gram_count_c+1;
        double s=(double) n_gram_count_c/(double)(n_gram_count_a+n_gram_count_b);
        return s;
    }
    static public String isRelevant(String a, String b)
    {
        String s1=b;
        String is_found=new String();
        String[] Ngram_a=getNGrams(a,3);
        String[] words=s1.split("\\s");
        for(String w:words){
           if(w.length()>3)
            {
                String[] Ngram_w=getNGrams(w,3);
            double get_distance=getDistance(Ngram_a,Ngram_w);
            if(get_distance>0.4)
            {
                is_found="true";
                break;
            }
            else
            {
                is_found="false";
            }
            }
        }
 return is_found;
    }
    public static void main(String[] args) {
        String[] houseNGrams=getNGrams("house",3);
        String s=Arrays.toString(houseNGrams);
        System.out.println(s);
        String[] trousersNGrams =getNGrams("trousers",3);
        String s1=Arrays.toString(trousersNGrams);
        System.out.println(s1);
        String[] shared_Ngram=  getShared(houseNGrams,trousersNGrams);
        String s2=Arrays.toString(shared_Ngram);
        System.out.println(s2);
        System.out.println(getDistance(houseNGrams,trousersNGrams));
        System.out.println(isRelevant("house","the house is clean"));
    }
}
