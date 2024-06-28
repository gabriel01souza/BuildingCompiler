.assembly extern mycolic {} 
 .assembly _codigo_objeto{} 
 .module _codigo_objeto.exe 
 .class public _UNICA{  
 .method static public void _principal() { 
.entrypoint; 
.locals (int64 lado) 
.locals (int64 area) 
ldc.i8 0 
stloc lado 
ldc.i8 0 
stloc area 
.locals (string texto) 
ldstr "valor inicial" 
stloc texto 
ldstr "Digite um valor para lado: " 
call void [mscorlib]System.Console::WriteLine(string) 
call string [mscorlib]System.Console::ReadLine() 
call int64 [mscorlib]System.Int64::Parse(string) 
stloc lado 
ldloc lado 
conv.r8 
ldc.i8 0 
conv.r8 
cgt 
brfalse r_2 
ldloc lado 
conv.r8 
ldloc lado 
conv.r8 
mul 
conv.i8 
stloc area 
ldloc area 
conv.r8 
conv.i8 
call void [mscorlib]System.Console::WriteLine(int64) 
r_3: 
ldloc lado 
conv.r8 
ldc.i8 1 
conv.r8 
sub 
conv.i8 
stloc lado 
ldloc lado 
conv.r8 
ldc.i8 0 
conv.r8 
ceq 
brfalse r_3 
ldloc lado 
conv.r8 
conv.i8 
call void [mscorlib]System.Console::WriteLine(int64) 
br r_2 
r_3: 
ldloc lado 
conv.r8 
ldc.i8 0 
conv.r8 
ceq 
brfalse r_4 
ldstr "Lado é zero" 
call void [mscorlib]System.Console::WriteLine(string) 
br r_2 
r_4: 
ldstr "Lado é negativo" 
call void [mscorlib]System.Console::WriteLine(string) 
r_2: 
ret 
} 
}