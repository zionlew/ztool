/**----------------------------------------------------------+
 +   PGM-ID  :   Test.java
 +   Author  :   LH
 +   Date    :   2022年6月3日
 +   Function:   遍历所有引用的类和方法
 +               
 +-----------------------------------------------------------*/
package common;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.zion.def.TransDefMain;

public class ShowAllCallMethod {

	public static void main(String[] args) throws IOException {

		ClassReader classReader = new ClassReader(TransDefMain.class.getName());
		
		
		ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5) {
			@Override
			public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
					String[] exceptions) {
				return new MethodVisitor(Opcodes.ASM5,
						super.visitMethod(access, name, descriptor, signature, exceptions)) {
					@Override
					public void visitMethodInsn(int opcode, String owner, String name, String descriptor,
							boolean isInterface) {
						super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
//						System.out.println("method call :" + owner.replace("/", ".") + "." + name + " declares " + descriptor);
						System.out.println("method call :" + owner.replace("/", ".") + "." + name);

					}
				};
			}
		};
		classReader.accept(classVisitor, Opcodes.ASM5);
	}
}
