package  com.kona.kms.web.profile;

 import  java.io.BufferedInputStream;
 import  java.io.BufferedOutputStream;
 import  java.io.BufferedReader;
 import  java.io.ByteArrayInputStream;
 import  java.io.ByteArrayOutputStream;
 import  java.io.FileInputStream;
 import  java.io.FileNotFoundException;
 import  java.io.FileOutputStream;
 import  java.io.IOException;
 import  java.io.InputStream;
 import  java.io.InputStreamReader;
 import  java.io.OutputStream;
 import  java.math.BigInteger;
 import  java.security.SecureRandom;

 import  org.bouncycastle.crypto.AsymmetricBlockCipher;
 import  org.bouncycastle.crypto.AsymmetricCipherKeyPair;
 import  org.bouncycastle.crypto.DataLengthException;
 import  org.bouncycastle.crypto.encodings.OAEPEncoding;
 import  org.bouncycastle.crypto.encodings.PKCS1Encoding;
 import  org.bouncycastle.crypto.engines.DESEngine;
 import  org.bouncycastle.crypto.engines.RSAEngine;
 import  org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
 import  org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
 import  org.bouncycastle.crypto.params.RSAKeyParameters;
 import  org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
 import  org.bouncycastle.util.encoders.Hex;
// import  org.dev2dev.security.crypto.blockcipher.BlockCipherTool;


 public   class  RSAUtils  {
    
	 int  keylength = 1024;
     int  certainty = 20;
     int exponent = 0x3;
     
     RSAKeyGenerationParameters keyparam;
     AsymmetricBlockCipher   eng  =   null;
     RSAKeyPairGenerator  pGen  =   null;
     AsymmetricCipherKeyPair  pair  =   null;
    
     public  RSAUtils(){
    	 
     } 
    
     public  String getName(){
         return   "RSA";
     }
    

     public   void  setKeyLength(int  rsakeylength){
    	 if (rsakeylength == 512 || rsakeylength == 768 || rsakeylength == 1024 || rsakeylength == 2048 || rsakeylength ==1408 || rsakeylength==1152)
    		 keylength = rsakeylength;
     } 
    
     public   void  setCertaintyOfPrime(int  certaintyofprime){
        certainty = certaintyofprime;
     }
     
     public   void  setExponent(int  publicExponent){
    	 if(publicExponent != 0){
    		 exponent = publicExponent;	 
    	 }
    	 
      }
    

     public   void  initRSAKeyPair(){
        RSAKeyGenerationParameters rsaparam = new  RSAKeyGenerationParameters(BigInteger.valueOf( exponent ), new  SecureRandom(),  this.keylength,  this.certainty);        
        this .keyparam  =  rsaparam;

        RSAKeyPairGenerator  pGen  =   new  RSAKeyPairGenerator();
        pGen.init(keyparam);
        pair  =  pGen.generateKeyPair();        
        pair.getPublic();
     }
    

     public   void  setRSAKeyPair(RSAKeyParameters pubparam, RSAPrivateCrtKeyParameters privparam)
     {
        AsymmetricCipherKeyPair newpair = new  AsymmetricCipherKeyPair(pubparam,privparam);
        pair = newpair;
        
    } 
    
     public  RSAKeyParameters getPublicKey()
     {
             return  (RSAKeyParameters)pair.getPublic();            
    } 
     
     public  RSAPrivateCrtKeyParameters getPrivateKey()
     {
         return  (RSAPrivateCrtKeyParameters)pair.getPrivate();
    } 
     
     public   void  setRSAMode( int  mode)
     {
        eng  =   new  RSAEngine();  
          if  (mode == 2 )
            eng  =   new  PKCS1Encoding(eng);
         else 
            eng  =   new  OAEPEncoding(eng);     // mode==3 
     } 
    
     public  String  encrypt(String input)
     {

         byte [] inputdata = Hex.decode(input);

         eng.init( true , pair.getPublic());        
        
         try 
          {
            inputdata  =  eng.processBlock(inputdata,  0 , inputdata.length);
        } 
         catch  (Exception e)
         {
            e.printStackTrace();
        }         
        
         return   new  String(Hex.encode(inputdata));
    } 
    
     public   byte []  encrypt( byte [] inputdata)
     {
         byte [] outputdata = null ;

         eng.init( true , pair.getPublic());        
        
         try 
          {
            inputdata  =  eng.processBlock(inputdata,  0 , inputdata.length);
            outputdata = new   byte [eng.getOutputBlockSize()];
            outputdata = inputdata;
        } 
         catch  (Exception e)
         {
            e.printStackTrace();
        }         
        
         return  outputdata;
    } 
    
   
     public   byte [] encryptPro( byte [] inputload)
     {
        ByteArrayInputStream inputstream = new  ByteArrayInputStream(inputload);        
        ByteArrayOutputStream outputstream = new  ByteArrayOutputStream();
                
        
         eng.init( true , pair.getPublic());        
        
         int  inBlockSize  = this .eng.getInputBlockSize() ;
         int  outBlockSize  =   this .eng.getOutputBlockSize();

         try   {

            encryptPro(inputstream, outputstream);
                   
        }   catch  (DataLengthException e)  {
            e.printStackTrace();
        }   catch  (IllegalStateException e)  {
            e.printStackTrace();
        }   catch (Exception e) {
            e.printStackTrace();
        }     
        
         byte []  outputload = outputstream.toByteArray(); 
         
         return  outputload;
    } 
 
     public   void  encrypt(BufferedInputStream inputstream,BufferedOutputStream outputstream)
     {
         eng.init( true , pair.getPublic());        
        
         int  inBlockSize  = this .eng.getInputBlockSize() ;
         int  outBlockSize  =   this .eng.getOutputBlockSize();

         byte [] inblock  =   new   byte [inBlockSize];
         byte [] outblock  =   new   byte [outBlockSize];

         byte [] rv  =   null ;
         try   {
             while  (inputstream.read(inblock,  0 , inBlockSize)  >   0 )
             {
                outblock  =  eng.processBlock(inblock,  0 , inBlockSize);
                    rv  =  Hex.encode(outblock,  0 , outBlockSize);
                    outputstream.write(rv,  0 , rv.length);
                    outputstream.write( '\n' );

            } 
                        
        }   catch  (DataLengthException e)  {
            e.printStackTrace();
        }   catch  (IllegalStateException e)  {
            e.printStackTrace();
        }   catch (Exception e) {
            e.printStackTrace();
        }     
        
    } 
 

     public   void  encryptPro(InputStream inputstream,OutputStream outputstream)
     {
         eng.init( true , pair.getPublic());        
        
         int  inBlockSize  = this .eng.getInputBlockSize() ;
         int  outBlockSize  =   this .eng.getOutputBlockSize();

         byte [] inblock  =   new   byte [inBlockSize];
         byte [] outblock  =   new   byte [outBlockSize];
        
        
         byte [] rv  =   null ;
        
         try   {

              int  padding_size = inBlockSize - (inputstream.available()  %  inBlockSize);
             
            outputstream.write((padding_size + "" ).getBytes()); 
            outputstream.write( '\n' );
            
             while  (inputstream.read(inblock,  0 , inBlockSize)  >   0 )                
             {                                
                outblock  =  eng.processBlock(inblock,  0 , inBlockSize);
                rv  =  Hex.encode(outblock,  0 , outBlockSize);
                
                 outputstream.write(rv,  0 , rv.length);                                    
                
                outputstream.write( '\n' );              
            } 
                        
        }   catch  (DataLengthException e)  {
            e.printStackTrace();
        }   catch  (IllegalStateException e)  {
            e.printStackTrace();
        }   catch (Exception e) {
            e.printStackTrace();
        }     
        
    } 
    
     public  String decrypt(String input)
     {

         byte [] inputdata = Hex.decode(input);
        
        eng.init( false ,pair.getPrivate());
        
         try 
          {
            inputdata = eng.processBlock(inputdata, 0 ,inputdata.length);            
        } 
         catch (Exception e)
         {
            e.printStackTrace();
        } 
        
         return   new  String(Hex.encode(inputdata));
        
    } 
    
     public   byte []  decrypt( byte [] inputdata)
     {
         byte [] outputdata = null ;

         eng.init( false , pair.getPrivate());        
        
         try 
          {
            inputdata  =  eng.processBlock(inputdata,  0 , inputdata.length);
            outputdata = new   byte [eng.getOutputBlockSize()];
            outputdata = inputdata;
        } 
         catch  (Exception e)
         {
            e.printStackTrace();
        }         
        
         return  outputdata;
    } 
 
     public   byte [] decryptPro( byte [] inputload)
     {

        ByteArrayInputStream inputstream = new  ByteArrayInputStream(inputload);        
        ByteArrayOutputStream outputstream = new  ByteArrayOutputStream();
    
         eng.init( false , pair.getPrivate());    
        
         int  inBlockSize  = this .eng.getInputBlockSize() ;
         int  outBlockSize  =   this .eng.getOutputBlockSize();
                
         try   {

             this .decryptPro(inputstream, outputstream);
        }   catch  (DataLengthException e)  {
            e.printStackTrace();
        }   catch  (IllegalStateException e)  {
            e.printStackTrace();
        }  
         catch (Exception e)
         {
            e.printStackTrace();
        } 
        
         // byte[] outputload=new byte[outputstream.size()]; 
          byte [] outputload = outputstream.toByteArray();      
         
         return  outputload;
    }     
    

     public   void  decryptPro(InputStream inputstream, OutputStream outputstream)
     {
         eng.init( false , pair.getPrivate());    
      
        BufferedReader br  =   new  BufferedReader( new  InputStreamReader(inputstream));
        
         int  inBlockSize  = this .eng.getInputBlockSize() ;
         int  outBlockSize  =   this .eng.getOutputBlockSize();
        
         int  lines;

         byte [] outblock  =   new   byte [outBlockSize];
        
        String rv  =   null ;
         int  inL = 0 ;
         byte [] last = null ;
        
         try   {
             int  amout = inputstream.available();
            lines = amout / (inBlockSize * 2 );
             rv = br.readLine();                                
           
              int  padding_size = Integer.parseInt(rv);

             while  ((rv  =  br.readLine())  !=   null )
             {
                lines -- ;

                inL = rv.length() / 2 ;  
                last = new   byte [inL];
                last  =  Hex.decode(rv);
                
                outblock  =  eng.processBlock(last,  0 , inBlockSize);
                
                 if (lines > 0 )
                 {                
                    outputstream.write(outblock,  0 , outBlockSize);
                } 
                 else 
                    outputstream.write(outblock,  0 , outBlockSize - padding_size);
                
            } 
            
        }   catch  (DataLengthException e)  {
            e.printStackTrace();
        }   catch  (IllegalStateException e)  {
            e.printStackTrace();
        }  
         catch (Exception e)
         {
            e.printStackTrace();
        } 
 
    }     
    
     public   void  ExportPublicKey(String Filename)
     {  
        String outfile  =  Filename;
        BufferedOutputStream outstream  =   null ;        

         try 
          {
            outstream  =   new  BufferedOutputStream( new  FileOutputStream(outfile));
        } 
         catch  (IOException fnf)
         {
            System.exit( 1 );
        }         
        
        
        RSAKeyParameters mypubkey = this .getPublicKey();
        BigInteger mypubkey_modulus = mypubkey.getModulus();        
        BigInteger mypubkey_exponent = mypubkey.getExponent();
         
         try 
          {
            outstream.write(mypubkey_modulus.toString().getBytes());
            outstream.write( '\n' );
            outstream.write(mypubkey_exponent.toString().getBytes());            
            outstream.flush();
            outstream.close();
        } 
         catch  (IOException closing)
         {
            closing.printStackTrace();
        } 
        
    } 
    

     public  RSAKeyParameters ImportPublicKey(String Filename)
     {
        String infile  =  Filename;
        BufferedInputStream instream  =   null ;
        RSAKeyParameters RSAParam = null ;
         try 
          {
            instream  =   new  BufferedInputStream( new  FileInputStream(infile));
        } 
         catch  (FileNotFoundException fnf)
         {
            System.exit( 1 );
        } 
        
        BufferedReader br  =   new  BufferedReader( new  InputStreamReader(instream));
        BigInteger mypubkey_modulus = null ;        
        BigInteger mypubkey_exponent = null ;        
        String readstr = null ;

         try   {
            
            readstr  =  br.readLine();
            
            mypubkey_modulus =   new  BigInteger(readstr);           
         
            readstr  =  br.readLine();
            mypubkey_exponent = new  BigInteger(readstr);
            
            RSAParam = new  RSAKeyParameters( false ,mypubkey_modulus,mypubkey_exponent);
            
        }   catch  (DataLengthException e)  {
            e.printStackTrace();
        }   catch  (IllegalStateException e)  {
            e.printStackTrace();
        }  
         catch (Exception e)
         {
            e.printStackTrace();
        }         
         return  RSAParam;

    } 
    
     public   void  ExportPrivateKey(String Filename)
     {
  
        String outfile  =  Filename;
        BufferedOutputStream outstream  =   null ;        

         try 
          {
            outstream  =   new  BufferedOutputStream( new  FileOutputStream(outfile));
        } 
         catch  (IOException fnf)
         {
            System.exit( 1 );
        }         
        
        
        RSAPrivateCrtKeyParameters myprivkey = this .getPrivateKey();
        
        BigInteger myprivkey_modulus = myprivkey.getModulus();
        BigInteger myprivkey_exponent = myprivkey.getExponent();
        BigInteger e = myprivkey.getPublicExponent();   // e is public 
         BigInteger dP = myprivkey.getDP();
        BigInteger dQ = myprivkey.getDQ();
        BigInteger p = myprivkey.getP();
        BigInteger q = myprivkey.getQ();
        BigInteger qInv = myprivkey.getQInv();
        

         try 
          {
            outstream.write(myprivkey_modulus.toString().getBytes());
            outstream.write( '\n' );
            outstream.write(e.toString().getBytes());   
            outstream.write( '\n' );
            outstream.write(myprivkey_exponent.toString().getBytes());            
            outstream.write( '\n' );
            outstream.write(p.toString().getBytes());   
            outstream.write( '\n' );
            outstream.write(q.toString().getBytes());
            outstream.write( '\n' );            
            outstream.write(dP.toString().getBytes());   
            outstream.write( '\n' );
            outstream.write(dQ.toString().getBytes());   
            outstream.write( '\n' );
            outstream.write(qInv.toString().getBytes());   
            outstream.write( '\n' );
            
            outstream.flush();
            outstream.close();
        } 
         catch  (IOException closing)
         {
            closing.printStackTrace();
        }    
        
    } 
    
  
     public  RSAPrivateCrtKeyParameters ImportPrivateKey(String Filename)
     {
        String infile  =  Filename;
        BufferedInputStream instream  =   null ;
        RSAPrivateCrtKeyParameters RSAPrivParam = null ;
        
         try 
          {
            instream  =   new  BufferedInputStream( new  FileInputStream(infile));
        } 
         catch  (FileNotFoundException fnf)
         {
            System.exit( 1 );
        } 
        
        BufferedReader br  =   new  BufferedReader( new  InputStreamReader(instream));
   
        
        BigInteger myprivkey_modulus = null ;
        BigInteger myprivkey_exponent = null ;
        BigInteger e = null ;
        BigInteger p = null ;
        BigInteger q = null ;
        BigInteger dP = null ;
        BigInteger dQ = null ;
        BigInteger qInv = null ;
        
        String readstr = null ;
         try   {
            
            readstr  =  br.readLine();                        
            myprivkey_modulus =   new  BigInteger(readstr);
            readstr  =  br.readLine();
            e =   new  BigInteger(readstr);
            readstr  =  br.readLine();               
            myprivkey_exponent =   new  BigInteger(readstr);
            readstr  =  br.readLine();                                            
            p =   new  BigInteger(readstr);
            readstr  =  br.readLine();                        
            q =   new  BigInteger(readstr);
            readstr  =  br.readLine();                        
            dP =   new  BigInteger(readstr);
            readstr  =  br.readLine();                        
            dQ =   new  BigInteger(readstr);            
            readstr  =  br.readLine();                        
            qInv =   new  BigInteger(readstr);

                      
            RSAPrivParam = new  RSAPrivateCrtKeyParameters(myprivkey_modulus, myprivkey_exponent,
                    e,p,q,dP,dQ,qInv);
            
        }   catch  (DataLengthException ex)  {
            ex.printStackTrace();
        }   catch  (IllegalStateException ex)  {
            ex.printStackTrace();
        }  
         catch (Exception ex)
         {
            ex.printStackTrace();
        }         
         return  RSAPrivParam;

    } 
    
   
     public   void  reset( byte [] block)
     {
         for ( int  i = 0 ;i < block.length;i ++ )
            block[i] = ( byte ) 0 ;
    } 
    
     public   void  padding( byte [] block,  int  off)
     {        
         for ( int  i = off;i < block.length;i ++ )
            block[i] = ( byte ) 0 ;
    } 
    
    
} 