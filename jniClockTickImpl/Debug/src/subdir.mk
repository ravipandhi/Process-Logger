################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/processLogger_lli_Main.c 

OBJS += \
./src/processLogger_lli_Main.o 

C_DEPS += \
./src/processLogger_lli_Main.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -I/usr/local/java/jdk1.8.0_112/include/linux -I/usr/local/java/jdk1.8.0_112/include -O0 -g3 -Wall -c -fmessage-length=0 -shared -fPIC -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


