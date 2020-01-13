/*This class represents record from the file as object*/
public  class Person  {
	
	Integer id;
	String name;
	String gender;
	String address;
	int i=1;
	
	public  void setValue(String value)
	{
		switch(i)
		{
			case 1:
			{
				this.id = Integer.parseInt(value);
				this.i++;
				break;
			}
			
			case 2:
			{
				this.name  = value;
				this.i++;
				break;
			}
			
			case 3:
			{
				this.gender  = value;
				this.i++;
				break;
			}
			
			case 4:
			{
				this.address  = value;
				this.i++;
				break;
			}
		}
		
	}
	
	// Function for printing the fields of object
	public void Print()
	{
		System.out.println("id : "+ this.id);
		System.out.println("name : "+ this.name);
		System.out.println("gender : "+ this.gender);
		System.out.println("address : "+ this.address);
		System.out.println("------------------------------");
	}
	
	// Provide record in csv format
	public String getCSV()
	{
		String  csv = id+","+name+","+gender+","+address+",";
		return csv;
		
	}
}

