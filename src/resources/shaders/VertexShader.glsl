#version 150 core

in vec4 in_Position;
in vec4 in_Colour;

out vec4 pass_Colour;

void main(void) {
	gl_Position = in_Position;
	pass_Colour = in_Colour;
}