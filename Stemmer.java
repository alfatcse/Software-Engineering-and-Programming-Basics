package assignment5;
public class Stemmer {
    static public String getNGrams(String keyword,int number)
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
        }
        return Ngram;
    }
    static public String getShared(String a,String b)
    {
        int i=a.length();
        String[] first=new String[i];
        String shared=new String();
        int p=0,q=0;
        for(int m=0;m<i;m++)
        {
         if(a.charAt(m)==',')
          {
              first[p]=a.substring(q,m);
              q=m+1;
              p++;
          }
        }
        first[p]=a.substring(q,i);
        int j=b.length();
        String[] second=new String[j];
        int t=0,z=0;
        for(int n=0;n<j;n++)
        {
            if(b.charAt(n)==',')
            {
                second[t]=b.substring(z,n);
                z=n+1;
                t++;
            }
        }
        second[t]=b.substring(z,j);
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
        return shared;
    }
    static public double getDistance(String a,String b)
    {
        int i=a.length();
        String[] first=new String[i];
        int n_gram_count_a=0;
        int p=0,q=0;
        for(int m=0;m<i;m++)
        {
            if(a.charAt(m)==',')
            {
                first[p]=a.substring(q,m);
                q=m+1;
                p++;
                n_gram_count_a=n_gram_count_a+1;
            }
        }
        int j=b.length();
        String[] second=new String[j];
        int t=0,z=0;
        int n_gram_count_b=0;
        for(int n=0;n<j;n++)
        {
            if(b.charAt(n)==',')
            {
                second[t]=b.substring(z,n);
                z=n+1;
                t++;
                n_gram_count_b=n_gram_count_b+1;
            }
        }
        n_gram_count_a=n_gram_count_a+1;
        n_gram_count_b=n_gram_count_b+1;
        String matched_n_gram=getShared(a,b);
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
        int j=b.length();
        String[] second=new String[j];
        int t=0,z=0;
        for(int n=0;n<j;n++)
        {
            if(b.charAt(n)==' ')
            {
               second[t]=b.substring(z,n);
                z=n+1;
                t++;
            }
        }
       second[t]=b.substring(z,j);
        int h=0;
       String is_found=new String();
        for(int y=0;y<=t;y++)
        {
                if (a!=null)
                {
                    if(second[y].equals(a))
                    {
                        is_found="true";
                        break;
                    }
                    else
                    {
                        is_found= "false";
                    }
                }
        }
        System.out.println(is_found);
 return is_found;
    }
    public static void main(String[] args) {
        String houseNGrams=getNGrams("house",3);
        System.out.println(houseNGrams);
        String trousersNGrams =getNGrams("trousers",3);
        System.out.println(trousersNGrams);
        System.out.println(getShared(houseNGrams,trousersNGrams));
        System.out.println(getDistance(houseNGrams,trousersNGrams));
        isRelevant("house","trousers");

    }
}
