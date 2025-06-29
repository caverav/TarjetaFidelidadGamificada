{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  buildInputs = [
    pkgs.openjdk21
    pkgs.maven
  ];

  shellHook = ''
    export JAVA_HOME=${pkgs.openjdk21.home}
  '';
}
