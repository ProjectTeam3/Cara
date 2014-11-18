import sys 
import random
import time 
import pickle
from PyQt4.QtCore import *
from PyQt4.QtGui import *
import struct



class InputDialog(QWidget):
    def __init__(self,parent=None):
        super(InputDialog, self).__init__(parent)
        self.createLayout()
        self.ss = ""
        self.sss = ""
        self.cm = bytes()
        self.enfile = bytes()
        self.text = bytes()
        self.outfile = bytes()
        self.outtext = bytes()
        
    def createLayout(self):       
        self.button14=QPushButton('Choose File',self)
        self.button11=QPushButton('Encryption',self)
        self.fileLineEdit13 = QLineEdit()

        self.connect(self.button14,SIGNAL('clicked()'),self.openFile)
        self.connect(self.button11,SIGNAL("clicked()"),self.Encryfile)

        
        fileLayout = QHBoxLayout()
        fileLayout.addWidget(self.button11)
        fileLayout.addWidget(self.button14)
        fileLayout.addWidget(self.fileLineEdit13)
  
        
        self.label4=QLabel(self.tr("Out put to:  "))
        self.fileLineEdit42 = QLineEdit()
        self.button43=QPushButton('Choose File',self)
        self.connect(self.button43,SIGNAL('clicked()'),self.openFile2)
        
        oLayout = QHBoxLayout()
        oLayout.addWidget(self.label4)
        oLayout.addWidget(self.fileLineEdit42)
        oLayout.addWidget(self.button43)


        self.outputButton=QPushButton("Output!")  
        self.aboutButton=QPushButton("ABOUT")  
        self.shuffleButton=QPushButton("SHUFFLE") 
        self.connect(self.aboutButton,SIGNAL("clicked()"),self.about)
        self.connect(self.shuffleButton,SIGNAL("clicked()"),self.shuffle)
        self.connect(self.outputButton,SIGNAL("clicked()"),self.output) 
        
        

        
        outputLayout = QHBoxLayout()
        outputLayout.addWidget(self.outputButton)
        
        aboutLayout = QHBoxLayout()
        aboutLayout.addWidget(self.aboutButton)   
  
        hbox = QHBoxLayout()
        pixmap = QPixmap("main.jpg")
        lbl = QLabel()
        lbl.setPixmap(pixmap)
        hbox.addWidget(lbl)
        
        self.label_ans=QLabel(self.tr("Answer"))  
        self.label_ans.setFrameStyle(QFrame.Panel|QFrame.Sunken)
        ansLayout = QHBoxLayout()
        ansLayout.addWidget(self.label_ans) 
        
        totalLayout = QVBoxLayout()
        totalLayout.addLayout(fileLayout)


        #totalLayout.addLayout(ansLayout)
        totalLayout.addLayout(hbox)
        totalLayout.addLayout(oLayout)
        
        totalLayout.addLayout(outputLayout)
        totalLayout.addLayout(aboutLayout)
                
        self.setLayout(totalLayout)
        self.setGeometry(300, 300, 900, 250)
        self.setWindowTitle('Calculation of the speed of Type II Radio Burst Based on Digital Image Processing and Its Application')
        self.show()


    def about(self):
        aboutbox = QMessageBox.about(self, 'About', 'Made by W4w using PyQt4.  ') 
        
    def openFile(self):  
        s=QFileDialog.getOpenFileName(self,"Open file dialog")  
        self.fileLineEdit13.setText(str(s))
        self.sss = s
          
    def openFile2(self):  
        s=QFileDialog.getOpenFileName(self,"Open file to Output")  
        self.fileLineEdit42.setText(str(s))
        self.ss = s
        
        
        #self.outfile = s
        
    def showDialog(self): 
        text,ok=QInputDialog.getText(self,'InputDialog', 'need to build after:') 
        if ok: 
            self.label.setText(text) 
            
    def output(self): 
        text,ok=QInputDialog.getText(self,'InputDialog', 'need to build after:') 
        if ok: 
            self.label.setText(text)
            

 
    def shuffle (self):
        prim = 10
       
       

    def Encrytext (self):
        self.entext = self.fileLineEdit23.text()
        self.entext = self.entext.encode()
        #print(self.enfile)
        c = []
        for each_flick in self.entext:
            c.append(pow(int(each_flick), self.em, self.nm))
        #c = pow(int(self.enfile), self.em, self.nm)
        print(c)
        print(self.em)
        print(self.nm)
        self.label_ans.setText(str(c))
    
    def Encryfile (self):
        with open(self.sss, 'rb') as f:
            self.enfile = f.read()
       
    
    def Decrytext (self):  
        n = int(self.fileLineEdit23.text())
       
        self.outtext = n
        
    def Decryfile (self): 
        mm = []



def main():
    app=QApplication(sys.argv)
    icon=InputDialog() 
    sys.exit(app.exec_())
if __name__ == '__main__':
    main()