import java.util.*;

public class Solution
{
	public static void main(String args[])
	{
		 Scanner scan = new Scanner(System.in);
		 String userInput;
		 String userInputEdit;
		 String numericArray[];
		 String operatorArrayTemp[];
		 String operatorArray[];

		 int indexOrder[];
		 userInput = scan.nextLine();
		 ArrayList RPNOrder;
		 boolean operatorFound = true;
		 int currIndex = 0;
		 List base10list = new LinkedList();
		 List operators = new LinkedList();

		 userInput = (userInput.toLowerCase()).replaceAll("\\s","");
		 userInputEdit = userInput;
		 numericArray = userInputEdit.split("\\+|-|\\*|\\/|_");
		 operatorArrayTemp = userInputEdit.split("[0-9a-zA-Z|_]");

		 operatorArray = new String[(numericArray.length/2)-1];
		 indexOrder = new int[numericArray.length];

		 int j = 0;
		 for(int i = 0; i < operatorArrayTemp.length; i++)
		 {
		  if(operatorArrayTemp[i].equals("+") || operatorArrayTemp[i].equals("-") || operatorArrayTemp[i].equals("*") || operatorArrayTemp[i].equals("/"))
		  {
		   operatorArray[j] = operatorArrayTemp[i];
		   j++;
		  }
		 }
		 j = 0;

		 for(int i = 0; i < operatorArray.length; i++)
		 {
			 operators.add(operatorArray[i]);
		 }

		 for(int i = 0; i < numericArray.length; i+=2)
		 {
			base10list.add(BaseN(numericArray[i],Integer.parseInt(numericArray[i+1])));
		 }

		 System.out.println(calcSolver(operators, base10list));

	}


	public static int BaseN(String num, int base)
	{
		String Alpha = "123456789abcdef";
		int j = 1;
		int numLen = num.length() - 1;
		int base10num = 0;
		int count = 0;

		while(0 <= numLen)
		{
			for(int i = 0; i < base; i++)
			{
				if(num.charAt(numLen) == Alpha.charAt(i))
				{
					base10num += ((i + 1) * j);
					break;
				}
			}

			j = j * base;
			numLen--;
		}

		return base10num;
	}

	public static int calcSolver(List ops, List nums)
	{
		int i = 0;
		int amount = 0;

		while(i < ops.size())
		{
			if(ops.get(i).equals("*") || ops.get(i).equals("/"))
			{
				if(ops.get(i).equals("*"))
				{
					amount = (Integer.parseInt(nums.get(i).toString()) * Integer.parseInt(nums.get(i+1).toString()));
				}
				else
				{
					amount = (Integer.parseInt(nums.get(i).toString()) / Integer.parseInt(nums.get(i+1).toString()));
				}

				ops.remove(i);
				nums.remove(i+1);
				nums.set(i, amount);
				i--;
			}
			//System.out.println(nums.toString() + " : " + ops.toString());
			i++;
		}

		i = 0;

		while(i < ops.size())
		{
			if(ops.get(i).equals("+") || ops.get(i).equals("-"))
			{
				if(ops.get(i).equals("+"))
				{
					amount = (Integer.parseInt(nums.get(i).toString()) + Integer.parseInt(nums.get(i+1).toString()));
				}
				else
				{
					amount = (Integer.parseInt(nums.get(i).toString()) - Integer.parseInt(nums.get(i+1).toString()));
				}

				ops.remove(i);
				nums.remove(i+1);
				nums.set(i, amount);
				i--;
			}
			//System.out.println(nums.toString() + " : " + ops.toString());
			i++;
		}

		return Integer.parseInt(nums.get(0).toString());
	}

}