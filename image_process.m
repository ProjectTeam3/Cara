function [result] = image_processing( )
I=imread('sample.gif');
thresh=graythresh(I);
I3=im2bw(I,thresh);
SE=strel('arbitrary',eye(5));
I4=imerode(I3,SE);
I2=imerode(I4,SE);
figure,imshow(I2);
data=zeros(1,99);
for i=1:99
    N=I2(:,i);
    number=0;
    tem=zeros(1,160);
    for j=1:160
        if N(j)==1
            number=number+1;
            tem(number)=j;
        end
    end
    if number>0
        data(i)=180-(tem(ceil(number/2))-1)*31/43;
    end
end
for i=1:99
    fprintf('%d\n',data(i));
end
result=zeros(1,3);
result(1)=data(24);
result(2)=data(50);
result(3)=data(81);
end

