#version 150 core

in vec4 in_Position;
in vec4 in_Colour;
in vec2 in_TexturePos;

out vec4 pass_Colour;
out vec2 pass_TexturePos;

void main(void) {
	gl_Position = in_Position;
	pass_Colour = in_Colour;
	pass_TexturePos = in_TexturePos;
}