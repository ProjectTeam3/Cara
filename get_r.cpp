#include <iostream>
#include <cstring>

using namespace std;


int main()
{
	double n=0,rr=0,r=0,f=0,temp=0;
	double min=0.0,max=999999.0;
	while(cin>>f){
	n=(f/9) * (f/9) / pow(10,12);
	cout<<n<<endl;
	min=0.0,max=99999.0;
	rr=(min+max)/2;
	temp=1.36 * pow(rr,(-2.1)) + 168 * pow(rr,(-6.13));
    while(fabs(n-temp) >= 0.0001){
		cout<<rr<<"-"<<temp<<endl;
		if(n > temp){
			max=rr;
			rr=(min + rr)/2;
		}
		else{
			min=rr;
			rr=(rr + max)/2;
		}
		temp=1.36 * pow(rr,(-2.1)) + 168 * pow(rr,(-6.13));
	}

    r=rr*6.963*pow(10,8);
	cout<<r<<endl;
	}
	return 0;
}
