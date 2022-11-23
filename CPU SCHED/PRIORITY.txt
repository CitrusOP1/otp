import java.util.*;
class priority
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int n,i,j,temp,time;
		System.out.print("Enter number of process : ");
		n=sc.nextInt();
		int AT[]=new int [n];
		int sort[]=new int [n];
		int index[]=new int [n];
		int pri[]=new int[n];
		int BT[]=new int [n];
		int CT[]=new int [n];
		int TAT[]=new int [n];
		int WT[]=new int [n];
		String P[]=new String [n];
	
		for(i=0;i<n;i++)
		{
			System.out.print("\nEnter name of process "+(i+1)+" : ");
			P[i]=sc.next();
		}
		System.out.println("Lower Number has Highest Priority.");
		for(i=0;i<n;i++)
		{
			System.out.print("\nEnter Priority of process "+(i+1)+" : ");
			pri[i]=sc.nextInt();
			sort[i]=pri[i];
		}
	
		for(i=0;i<n;i++)
		{
			AT[i]=0;
		}
		
		for(i=0;i<n;i++)
		{
			System.out.print("\nEnter Burst Time of process "+(i+1)+" : ");
			BT[i]=sc.nextInt();
		}
		
		
		for(i=0;i<n;i++)
		{
			for(j=i+1;j<n;j++)
			{
				if(sort[i]>sort[j])
				{
					temp=sort[j];
					sort[j]=sort[i];
					sort[i]=temp;
				}
			}
		}
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				if(sort[i]==pri[j])
				{
					index[i]=j;
					break;
				}
			}
		}
		
		System.out.println("\nPri =   ");
		for(i=0;i<n;i++)
		{
			System.out.print(pri[i]+"\t");
		}
		
		System.out.println("\nsort=   ");
		for(i=0;i<n;i++)
		{
			System.out.print(sort[i]+"\t");
		}
		System.out.println("\nindex=   ");
		for(i=0;i<n;i++)
		{
			System.out.print(index[i]+"\t");
		}
		
		time=0;
		for(i=0;i<n;i++)
		{
			time=time+BT[index[i]];
			CT[index[i]]=time;
		}
	
		
		for(i=0;i<n;i++)
		{
			TAT[i]=CT[i]-AT[i];
			WT[i]=TAT[i]-BT[i];
		}
		
		System.out.println("\nProcess \tPriority\tAT\tBT\tCT\tTAT\tWT");
		for(i=0;i<n;i++)
		{
			System.out.println("   "+P[i]+"\t\t"+pri[i]+"\t\t"+AT[i]+"\t"+BT[i]+"\t"+CT[i]+"\t"+TAT[i]+"\t"+WT[i]);
		}
	}
}
